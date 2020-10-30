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
import net.kdks.constant.HttpStatusCode;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.htky.BaishiResult;
import net.kdks.model.htky.BaishiTraceItems;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * 百世快递业务实现
 * 
 * @author: wangze
 * @date: 2020年9月22日 下午6:08:44
 */
public class ExpressBaishiHandler implements ExpressHandler {

	private BaishiConfig baishiConfig;
	
	public ExpressBaishiHandler(BaishiConfig baishiConfig) {
		this.baishiConfig = baishiConfig;
	}
	
    @Override
    public ExpressResult getExpressInfo(ExpressParam expressParam) {
        String requestUrl = "http://edi-q9.ns.800best.com/kd/api/process";
        if(baishiConfig.getIsProduct() == 0) {
			requestUrl = "http://kdtest.800best.com/kd/api/process";
		}
        String partnerID = baishiConfig.getPartnerID();
        String secretKey = baishiConfig.getSecretKey();
        
        String serviceType = "KD_TRACE_QUERY";
        String messageType = "JSON";
        
        
        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> paramItemsMap = new HashMap<>();
        HashMap<String, Object> paramItems = new HashMap<>();
        String[] expressNo = {expressParam.getExpressNo()};
        paramItems.put("mailNo", expressNo);
        paramItemsMap.put("mailNos", paramItems);
        String bizData = JSON.toJSONString(paramItemsMap);
		paramMap.put("bizData", bizData);
		paramMap.put("serviceType", serviceType);
		paramMap.put("partnerID", partnerID);
		paramMap.put("messageType", messageType);
		
		String beforeDigestStr = bizData+secretKey;
		String sign = StringUtils.strTo16(DigestUtils.Md5(beforeDigestStr));
		paramMap.put("sign", sign.toLowerCase());
		
		Map<String,String> requestHeader = new HashMap<String,String>();
		
		requestHeader.put("ContentType", "application/x-www-form-urlencoded");
		String responseData = HttpRequest.post(requestUrl)
			    .addHeaders(requestHeader)
			    .body(StringUtils.buildMapToStrUrl(paramMap, "UTF-8"))
			    .execute().body();
        return disposeResult(responseData, expressNo[0]);
        
    }
    /**
	 * 结果处理
	 * 
	 * @param responseData
	 * @return
	 */
	private ExpressResult disposeResult(String responseData, String expressNo) {
		BaishiResult result = JSON.parseObject(responseData, BaishiResult.class);
		ExpressResult expressResult = new ExpressResult();
		expressResult.setOriginalResult(responseData);
		expressResult.setCom(ExpressCompanyCodeEnum.HTKY.getValue());
		expressResult.setNu(expressNo);
		if (result.getResult()) {
			
			expressResult.setStatus(HttpStatusCode.SUCCESS);
			List<BaishiTraceItems> routes = result.getTraceLogs().get(0).getTraces().getTrace();
			if(routes != null) {
				//官方默认正序，改为倒序
   				Collections.reverse(routes);
				List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
				for (BaishiTraceItems route : routes) {
					data.add(route);
				}
				expressResult.setState(data.get(0).getStatus());
				if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
					expressResult.setIscheck(CommonConstant.YES);
				}
				expressResult.setData(data);
			}
		} else {
			expressResult.setStatus(HttpStatusCode.EXCEPTION);
			expressResult.setMessage(result.getErrorDescription());
		}
		return expressResult;
	}

	@Override
	public OrderResult createOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public String getExpressCompanyCode() {
        return ExpressCompanyCodeEnum.HTKY.getValue();
    }

}

