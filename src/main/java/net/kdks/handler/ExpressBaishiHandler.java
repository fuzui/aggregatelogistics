package net.kdks.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.hutool.http.HttpRequest;
import net.kdks.config.BaishiConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressPriceParam;
import net.kdks.model.ExpressPriceResult;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.htky.BaishiResult;
import net.kdks.model.htky.BaishiTraceItems;
import net.kdks.model.htky.BaishiTraceLog;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * 百世.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressBaishiHandler implements ExpressHandler {

	private BaishiConfig baishiConfig;
	
	public ExpressBaishiHandler(BaishiConfig baishiConfig) {
		this.baishiConfig = baishiConfig;
	}
	
	/**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
    @Override
    public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam) {
        String requestUrl = "http://edi-q9.ns.800best.com/kd/api/process";
        if(baishiConfig.getIsProduct() == 0) {
			requestUrl = "http://kdtest.800best.com/kd/api/process";
		}
        String partnerId = baishiConfig.getPartnerId();
        String secretKey = baishiConfig.getSecretKey();
        
        String serviceType = "KD_TRACE_QUERY";
        String messageType = "JSON";
        
        
        HashMap<String, Object> paramMap = new HashMap<>(5);
        HashMap<String, Object> paramItemsMap = new HashMap<>(1);
        HashMap<String, Object> paramItems = new HashMap<>(1);
        List<String> expressNos = expressParam.getExpressNos();
        paramItems.put("mailNo", expressNos);
        paramItemsMap.put("mailNos", paramItems);
        String bizData = JSON.toJSONString(paramItemsMap);
		paramMap.put("bizData", bizData);
		paramMap.put("serviceType", serviceType);
		paramMap.put("partnerID", partnerId);
		paramMap.put("messageType", messageType);
		
		String beforeDigestStr = bizData+secretKey;
		String sign = StringUtils.strTo16(DigestUtils.md5Digest(beforeDigestStr));
		paramMap.put("sign", sign.toLowerCase());
		
		Map<String,String> requestHeader = new HashMap<String,String>(1);
		
		requestHeader.put("ContentType", "application/x-www-form-urlencoded");
		String responseData = HttpRequest.post(requestUrl)
			    .addHeaders(requestHeader)
			    .body(StringUtils.buildMapToStrUrl(paramMap, "UTF-8"))
			    .execute().body();
        return disposeResult(responseData, expressParam);
        
    }
    /**
	 * 结果处理
	 * 
	 * @param responseData
	 * @return 查询结果
	 */
	private ExpressResponse<List<ExpressResult>> disposeResult(String responseData, ExpressParam expressParam) {
		List<String> expressNos = expressParam.getExpressNos();
		BaishiResult result = JSON.parseObject(responseData, BaishiResult.class);
		List<ExpressResult> expressResults = new ArrayList<ExpressResult>();
		if (result.getResult()) {
			//此结果必然存在不会出现空指针，无需判断
			if(result.getTraceLogs() == null || result.getTraceLogs().size() == 0) {
				return ExpressResponse.failed(CommonConstant.NO_INFO);
			}
			Map<String,BaishiTraceLog> traceLogMap = new HashMap<String, BaishiTraceLog>();
			for (BaishiTraceLog traceLog: result.getTraceLogs()) {
				traceLogMap.put(traceLog.getMailNo(), traceLog);
			}
			for (String expressNo: expressNos) {
				ExpressResult expressResult = new ExpressResult();
				if(expressParam.isViewOriginal()) {
					expressResult.setOriginalResult(responseData);
				}
				expressResult.setCom(ExpressCompanyCodeEnum.HTKY.getValue());
				expressResult.setNu(expressNo);
				List<BaishiTraceItems> routes = traceLogMap.get(expressNo).getTraces().getTrace();
				if(routes == null || routes.size() == 0) {
					expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
					expressResult.setMsg(CommonConstant.NO_INFO);
					expressResults.add(expressResult);
					continue;
				}
				//官方默认正序，改为倒序
   				Collections.reverse(routes);
   				ExpressData latestData = routes.get(0);
   				if(expressParam.isViewRoute()) {
   					List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
   					for (BaishiTraceItems route : routes) {
   						data.add(route);
   					}
   					expressResult.setData(data);
				}
   				expressResult.setState(latestData.getStatus());
				if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
					expressResult.setIscheck(CommonConstant.YES);
				}
				
				expressResults.add(expressResult);
			}
			return ExpressResponse.ok(expressResults);
		} 
		return ExpressResponse.failed(result.getErrorDescription());
		
	}

	/**
	 * 运费预估
	 * 
	 * @param expressPriceParam 起始省份、起始城市、目的身份、目的城市、重量、长、宽、高
	 * @return 运费
	 */
	@Override
	public ExpressResponse<ExpressPriceResult> getExpressPrice(ExpressPriceParam expressPriceParam) {
		return ExpressResponse.failed(CommonConstant.NO_SOPPORT);
	}
	
	/**
     * 创建订单
     * @param createOrderParam	下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @return	快递单号等信息
     */
	@Override
	public ExpressResponse<OrderResult> createOrder(CreateOrderParam createOrderParam) {
		return ExpressResponse.failed(CommonConstant.NO_SOPPORT);
	}
	
	/**
     * 获取当前快递公司编码
     * @return 快递公司编码
     */
    @Override
    public String getExpressCompanyCode() {
        return ExpressCompanyCodeEnum.HTKY.getValue();
    }

}

