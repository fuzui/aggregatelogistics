package net.kdks.handler;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import cn.hutool.http.HttpUtil;
import net.kdks.config.ShentongConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.constant.HttpStatusCode;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.sto.ShentongResult;
import net.kdks.model.sto.ShentongRoute;
import net.kdks.utils.DigestUtils;

/**
 * 申通.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressShentongHandler implements ExpressHandler {

	private ShentongConfig shentongConfig;
	
	public ExpressShentongHandler(ShentongConfig shentongConfig) {
		this.shentongConfig = shentongConfig;
	}
	
	/**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
	@Override
	public ExpressResult getExpressInfo(ExpressParam expressParam) {
		String requestUrl = "https://cloudinter-linkgatewayonline.sto.cn/gateway/link.do";
		if(shentongConfig.getIsProduct() == 0) {
			requestUrl = "http://cloudinter-linkgatewaytest.sto.cn/gateway/link.do";
		}
		String secretKey = shentongConfig.getSecretKey();
		String appkey = shentongConfig.getAppkey();
		String code = shentongConfig.getAppkey();

		Map<String, Object> paramMap = new HashMap<>(7);
		Map<String, Object> paramsItem = new HashMap<>(2);
		String[] expressNo = { expressParam.getExpressNo() };
		paramsItem.put("order", "desc");
		paramsItem.put("waybillNoList", expressNo);

		String beforeDigestStr = JSON.toJSONString(paramsItem) + secretKey;
		String dataDigest = Base64.getEncoder().encodeToString(DigestUtils.md5Digest(beforeDigestStr));
		paramMap.put("content", JSON.toJSONString(paramsItem));
		paramMap.put("api_name", "STO_TRACE_QUERY_COMMON");
		paramMap.put("from_appkey", appkey);
		paramMap.put("from_code", code);
		paramMap.put("to_appkey", "sto_trace_query");
		paramMap.put("to_code", "sto_trace_query");
		paramMap.put("data_digest", dataDigest);

		String responseData = HttpUtil.post(requestUrl, paramMap);
		return disposeResult(responseData, expressNo[0]);

	}

	/**
	 * 结果处理
	 * 
	 * @param responseData
	 * @return
	 */
	private ExpressResult disposeResult(String responseData, String expressNo) {
		ShentongResult result = new ShentongResult();
		ExpressResult expressResult = new ExpressResult();
		expressResult.setOriginalResult(responseData);
		expressResult.setCom(ExpressCompanyCodeEnum.STO.getValue());
		expressResult.setNu(expressNo);
		try {
			result = JSON.parseObject(responseData, ShentongResult.class);
		} catch (JSONException e) {
			String messageStart = "<errorMsg>";
			String messageEnd = "</errorMsg>";
			int strStartIndex = responseData.indexOf(messageStart);
	        int strEndIndex = responseData.indexOf(messageEnd);
	        String errorResult = responseData.substring(strStartIndex, strEndIndex).substring(messageStart.length());
	        expressResult.setStatus(HttpStatusCode.EXCEPTION);
			expressResult.setMessage(errorResult);
			return expressResult;
		}
		
		if (result.getSuccess()) {
			expressResult.setStatus(HttpStatusCode.SUCCESS);
			List<ShentongRoute> routes = result.getData().get(expressNo);
			if(routes != null && routes.size() != 0) {
				List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
				for (ShentongRoute route : routes) {
					data.add(route);
				}
				expressResult.setState(data.get(0).getStatus());
				if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
					expressResult.setIscheck(CommonConstant.YES);
				}
				expressResult.setData(data);
			}else {
				expressResult.setStatus(HttpStatusCode.EXCEPTION);
				expressResult.setMessage(CommonConstant.NO_INFO);
			}
			
		} else {
			expressResult.setStatus(HttpStatusCode.EXCEPTION);
			expressResult.setMessage(result.getErrorMsg());
		}
		return expressResult;
	}
	
	/**
     * 创建订单
     * @param createOrderParam	下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @return	快递单号等信息
     */
	@Override
	public OrderResult createOrder(CreateOrderParam createOrderParam) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * 获取当前快递公司编码
     * @return 快递公司编码
     */
	@Override
	public String getExpressCompanyCode() {
		return ExpressCompanyCodeEnum.STO.getValue();
	}

}
