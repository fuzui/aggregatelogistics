package net.kdks.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import net.kdks.config.ShunfengConfig;
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
import net.kdks.model.sf.Route;
import net.kdks.model.sf.RouteResps;
import net.kdks.model.sf.ShunfengResult;
import net.kdks.model.sf.WaybillNoInfo;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;

/**
 * 顺丰.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressShunfengHandler implements ExpressHandler {

	private ShunfengConfig shunfengConfig;
	private final static String SUCCESS_FLAG = "A1000";
	
	public ExpressShunfengHandler(ShunfengConfig shunfengConfig) {
		this.shunfengConfig = shunfengConfig;
	}
	
	/**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
    @Override
    public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam) {
    	String requestUrl = getRequestUrl();
        String serviceCode = "EXP_RECE_SEARCH_ROUTES";
        Map<String, Object> paramItemsMap = new HashMap<>(5);
		List<String> expressNos = expressParam.getExpressNos();
		paramItemsMap.put("checkPhoneNo", expressParam.getMobile());
		paramItemsMap.put("methodType", "1");
		paramItemsMap.put("trackingType", "1");
		paramItemsMap.put("language", "0");
		paramItemsMap.put("trackingNumber", expressNos);
		Map<String, Object> paramMap = getBaseParam(serviceCode, paramItemsMap);
		
		String responseData = HttpRequest.post(requestUrl)
			    .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
			    .form(paramMap)
			    .execute().body();
		return disposeResult(responseData,expressParam);
        
    }
    /**
     * 结果处理
     * @param responseData
     * @return
     */
    private ExpressResponse<List<ExpressResult>> disposeResult(String responseData, ExpressParam expressParam) {
    	List<String> expressNos = expressParam.getExpressNos();
    	ShunfengResult result = JSON.parseObject(responseData, ShunfengResult.class);
    	List<ExpressResult> expressResults = new ArrayList<ExpressResult>();
    	
		if(SUCCESS_FLAG.equals(result.getApiResultCode())) {
			if(result.getApiResultData().getSuccess()) {
				List<RouteResps> routeResps = result.getApiResultData().getMsgData().getRouteResps();
				if(routeResps == null || routeResps.size() == 0) {
					return ExpressResponse.failed(CommonConstant.NO_INFO);
				}
				Map<String,RouteResps> routeRespsMap = new HashMap<String, RouteResps>();
				for(RouteResps resps: routeResps) {
					routeRespsMap.put(resps.getMailNo(), resps);
				}
				for(String expressNo: expressNos) {
					ExpressResult expressResult = disposeRoute(routeRespsMap.get(expressNo), expressParam, responseData);
					expressResults.add(expressResult);
				}
				return ExpressResponse.ok(expressResults);
			}else {
				return ExpressResponse.failed(result.getApiResultData().getErrorMsg());
			}
		}
        return ExpressResponse.failed(result.getApiErrorMsg());
    }
    
    /**
     * 路由处理
     * @param routeResps
     * @param isOriginal
     * @param responseData
     * @return 
     */
    private ExpressResult disposeRoute(RouteResps routeResps,ExpressParam expressParam,String responseData) {
    	ExpressResult expressResult = new ExpressResult();
    	if(expressParam.isViewOriginal()) {
    		expressResult.setOriginalResult(responseData);
    	}
		expressResult.setCom(ExpressCompanyCodeEnum.SF.getValue());
		expressResult.setNu(routeResps.getMailNo());
		List<Route> routes = routeResps.getRoutes();
		if(routes == null || routes.size() == 0) {
			expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
			expressResult.setMsg(CommonConstant.NO_INFO);
			return expressResult;
		}
		//默认正序，改为倒序
		Collections.reverse(routes);
		ExpressData latestData = routes.get(0);
		if(expressParam.isViewRoute()) {
			List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
			for (Route route : routes) {
				data.add(route);
			}
			expressResult.setData(data);
		}
		expressResult.setState(latestData.getStatus());
		if(expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
			expressResult.setIscheck(CommonConstant.YES);
		}
    	return expressResult;
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
    	String requestUrl = getRequestUrl();
    	
        String serviceCode = "EXP_RECE_CREATE_ORDER";
        Map<String, Object> paramItemsMap = new HashMap<>(4);
        Map<String, Object> cargoDetailsMap = JSON.parseObject(JSON.toJSONString(createOrderParam.getCargoDetail()), HashMap.class);
        cargoDetailsMap.put("sourceArea", "CHN");
        
        List<Map<String, Object>> cargoDetailsList = new ArrayList<Map<String, Object>>();
        cargoDetailsList.add(cargoDetailsMap);
        
        List<Map<String, Object>> contactInfoList = new ArrayList<Map<String, Object>>();
        Map<String, Object> contactInfoSendMap = JSON.parseObject(JSON.toJSONString(createOrderParam.getSendContactInfo()), HashMap.class);
        contactInfoSendMap.put("contactType", 1);
        contactInfoSendMap.put("country", "CN");
    	Map<String, Object> contactInfoReceiptMap = JSON.parseObject(JSON.toJSONString(createOrderParam.getReceiptContactInfo()), HashMap.class);
    	contactInfoReceiptMap.put("contactType", 2);
    	contactInfoReceiptMap.put("country", "CN");	
        contactInfoList.add(contactInfoSendMap);
        contactInfoList.add(contactInfoReceiptMap);
        
		paramItemsMap.put("language", "zh-CN");
		paramItemsMap.put("orderId", createOrderParam.getOrderId());
		paramItemsMap.put("cargoDetails", cargoDetailsList);
		paramItemsMap.put("contactInfoList", contactInfoList);
		Map<String, Object> paramMap = getBaseParam(serviceCode, paramItemsMap);
		
		String responseData = HttpRequest.post(requestUrl)
			    .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
			    .form(paramMap)
			    .execute().body();
        
    	System.out.println(responseData);
		return disposeCreateOrderResult(responseData, createOrderParam.getOrderId());
	}
    
    /**
     * 结果处理
     * @param responseData
     * @return
     */
    private ExpressResponse<OrderResult> disposeCreateOrderResult(String responseData, String orderId) {
    	OrderResult orderResult = new OrderResult();
    	orderResult.setOrderId(orderId);
    	ShunfengResult result = JSON.parseObject(responseData, ShunfengResult.class);
		String successFlag = "A1000";
		if(successFlag.equals(result.getApiResultCode())) {
			if(result.getApiResultData().getSuccess()) {
				List<WaybillNoInfo> waybillNoInfoList = result.getApiResultData().getMsgData().getWaybillNoInfoList();
				if(waybillNoInfoList != null && waybillNoInfoList.size() != 0) {
					orderResult.setExpressNo(waybillNoInfoList.get(0).getWaybillNo());
					return ExpressResponse.ok(orderResult);
				}
			}else {
				return ExpressResponse.failed(result.getApiResultData().getErrorMsg());
			}
		}
        return ExpressResponse.failed(result.getApiErrorMsg());
    }
    
    private String getRequestUrl() {
    	String requestUrl = "https://sfapi.sf-express.com/std/service";
    	if(shunfengConfig.getIsProduct() == 0) {
    		requestUrl = "https://sfapi-sbox.sf-express.com/std/service";
    	}
    	return requestUrl;
    }
    
    private Map<String, Object> getBaseParam(String serviceCode,Map<String, Object> paramItemsMap){
    	Map<String, Object> paramMap = new HashMap<>(6);
    	String partnerId = shunfengConfig.getPartnerId();
        String requestId = shunfengConfig.getRequestId();
        Long timestamp = DateUtils.currentTimeMillis();
        String msgDigest = null;
        String checkWord = shunfengConfig.getCheckWord();
        paramMap.put("partnerID", partnerId);
		paramMap.put("requestID", requestId);
		paramMap.put("serviceCode", serviceCode);
		paramMap.put("timestamp", timestamp);
		String param = JSON.toJSONString(paramItemsMap);
		paramMap.put("msgData", param);
		
		StringBuilder beforeDigestStr = new StringBuilder(param);
		beforeDigestStr.append(timestamp).append(checkWord);
		try {
			msgDigest = URLEncoder.encode(beforeDigestStr.toString(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		msgDigest = Base64.getEncoder().encodeToString(DigestUtils.md5Digest(msgDigest));
		paramMap.put("msgDigest", msgDigest);
		return paramMap;
    }
    
    /**
     * 获取当前快递公司编码
     * @return 快递公司编码
     */
    @Override
    public String getExpressCompanyCode() {
    	return ExpressCompanyCodeEnum.SF.getValue();
    }

}

