package net.kdks.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import net.kdks.config.YuantongConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.yto.YuanTongErrorResult;
import net.kdks.model.yto.YuanTongResult;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * 圆通.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressYuantongHandler implements ExpressHandler {

	private YuantongConfig yuantongConfig;
	
	public ExpressYuantongHandler(YuantongConfig yuantongConfig) {
		this.yuantongConfig = yuantongConfig;
	}
	
	/**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
    @Override
    public ExpressResponse<ExpressResult> getExpressInfo(ExpressParam expressParam) {
    	
    	String secretKey = yuantongConfig.getSecretKey();
        String appKey = yuantongConfig.getAppkey();
        String userId = yuantongConfig.getUserId();
    	
    	String requestUrl = "http://openapi.yto.net.cn/service/waybill_query/v1/"+userId;
    	if(yuantongConfig.getIsProduct() == 0) {
			requestUrl = "http://opentestapi.yto.net.cn/service/waybill_query/v1/"+userId;
		}
        
        String format = "JSON";
        String method = "yto.Marketing.WaybillTrace";
        String v = "1";
        String timestamp = DateUtils.currentTimeStr();
        
        Map<String, Object> paramMap = new HashMap<>(8);
        Map<String, Object>[] paramItemsMap = new Map[1];
        paramItemsMap[0] = new HashMap<>(1);
		String expressNo = expressParam.getExpressNo();
		paramItemsMap[0].put("Number", expressNo);
		String param = null;
		try {
			param = URLEncoder.encode(JSON.toJSONString(paramItemsMap), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		paramMap.put("app_key", appKey);
		paramMap.put("format", format);
		paramMap.put("method", method);
		paramMap.put("timestamp", timestamp);
		paramMap.put("user_id", userId);
		paramMap.put("v", v);
		paramMap.put("param", param);
		
		StringBuilder beforeDigestStr = new StringBuilder(secretKey);
		beforeDigestStr.append("app_key").append(appKey)
			.append("format").append(format)
			.append("method").append(method)
			.append("timestamp").append(timestamp)
			.append("user_id").append(userId)
			.append("v").append(v);
		
		String dataDigest = StringUtils.strTo16(DigestUtils.md5Digest(beforeDigestStr.toString()));
		paramMap.put("sign", dataDigest.toUpperCase());
		
		String responseData = HttpRequest.post(requestUrl)
			    .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
			    .body(StringUtils.buildMapToStr(paramMap,"UTF-8"))
			    .execute().body();
		
        return disposeResult(responseData, expressNo);
        
    }
    /**
	 * 结果处理
	 * 
	 * @param responseData
	 * @return
	 */
	private ExpressResponse<ExpressResult> disposeResult(String responseData, String expressNo) {
		
		ExpressResult expressResult = new ExpressResult();
		expressResult.setOriginalResult(responseData);
		expressResult.setCom(ExpressCompanyCodeEnum.YTO.getValue());
		expressResult.setNu(expressNo);
		List<YuanTongResult> routes = new ArrayList<YuanTongResult>();
		try {
			routes = JSON.parseObject(responseData, new TypeReference<ArrayList<YuanTongResult>>(){});
			List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
			//官方默认正序，改为倒序
			Collections.reverse(routes);
			for (YuanTongResult route : routes) {
				data.add(route);
			}
			expressResult.setState(data.get(data.size() - 1).getStatus());
			if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
				expressResult.setIscheck(CommonConstant.YES);
			}
			expressResult.setData(data);
			return ExpressResponse.ok(expressResult);
		} catch (JSONException e) {
			YuanTongErrorResult errorResult = new YuanTongErrorResult();
			try {
				errorResult = JSON.parseObject(responseData, YuanTongErrorResult.class);
			} catch (JSONException e2) {
				String messageStart = "<reason>";
				String messageEnd = "</reason>";
				int strStartIndex = responseData.indexOf(messageStart);
		        int strEndIndex = responseData.indexOf(messageEnd);
		        String result = responseData.substring(strStartIndex, strEndIndex).substring(messageStart.length());
				errorResult.setMessage(result);
				
			}
			return ExpressResponse.failed(errorResult.getMessage());
		}
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
    	return ExpressCompanyCodeEnum.YTO.getValue();
    }

}

