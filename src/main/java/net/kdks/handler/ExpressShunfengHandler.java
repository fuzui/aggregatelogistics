package net.kdks.handler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import net.kdks.config.ShunfengConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.constant.HttpStatusCode;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.sf.Route;
import net.kdks.model.sf.ShunfengResult;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;

/**
 * 顺丰快递
 * 
 * @author: wangze
 * @date: 2020年9月22日 下午1:13:39
 */
public class ExpressShunfengHandler implements ExpressHandler {

	private ShunfengConfig shunfengConfig;
	
	public ExpressShunfengHandler(ShunfengConfig shunfengConfig) {
		this.shunfengConfig = shunfengConfig;
	}
    @Override
    public ExpressResult getExpressInfo(ExpressParam expressParam) {
    	String requestUrl = getRequestUrl();
        String serviceCode = "EXP_RECE_SEARCH_ROUTES";
        Map<String, Object> paramItemsMap = new HashMap<>();
		String[] expressNo = {expressParam.getExpressNo()};
		paramItemsMap.put("checkPhoneNo", expressParam.getMobile());
		paramItemsMap.put("methodType", "1");
		paramItemsMap.put("trackingType", "1");
		paramItemsMap.put("language", "0");
		paramItemsMap.put("trackingNumber", expressNo);
		Map<String, Object> paramMap = getBaseParam(serviceCode, paramItemsMap);
		
		String responseData = HttpRequest.post(requestUrl)
			    .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
			    .form(paramMap)
			    .execute().body();
		return disposeResult(responseData,expressNo[0]);
        
    }
    /**
     * 结果处理
     * @param responseData
     * @return
     */
    private ExpressResult disposeResult(String responseData, String expressNo) {
    	ShunfengResult result = JSON.parseObject(responseData, ShunfengResult.class);
		ExpressResult expressResult = new ExpressResult();
		expressResult.setOriginalResult(responseData);
		expressResult.setCom(ExpressCompanyCodeEnum.SF.getValue());
		expressResult.setNu(expressNo);
		if(result.getApiResultCode().equals("A1000")) {
			if(result.getApiResultData().getSuccess()) {
				
				expressResult.setStatus(HttpStatusCode.SUCCESS);
				List<Route> routes = result.getApiResultData().getMsgData().getRouteResps().get(0).getRoutes();
				List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
				for (Route route : routes) {
					data.add(route);
				}
				expressResult.setState(data.get(0).getStatus());
				if(expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
					expressResult.setIscheck(CommonConstant.YES);
				}
				expressResult.setData(data);
			}else {
				expressResult.setStatus(HttpStatusCode.EXCEPTION);
				expressResult.setMessage(result.getApiResultData().getErrorMsg());
			}
		}else {
			expressResult.setStatus(HttpStatusCode.EXCEPTION);
			expressResult.setMessage(result.getApiErrorMsg());
		}
        return expressResult;
    }

    @Override
	public OrderResult createOrder() {
    	String requestUrl = getRequestUrl();
    	
        String serviceCode = "EXP_RECE_CREATE_ORDER";
        Map<String, Object> paramItemsMap = new HashMap<>();
        Map<String, Object> cargoDetailsMap = new HashMap<>();
        
        cargoDetailsMap.put("count", 2.365);
        cargoDetailsMap.put("unit", "个");
        cargoDetailsMap.put("weight", 6.1);
        cargoDetailsMap.put("amount", 100.5111);
        cargoDetailsMap.put("currency", "HKD");
        cargoDetailsMap.put("name", "测试衣服1");
        cargoDetailsMap.put("sourceArea", "CHN");
        
        List<Map<String, Object>> cargoDetailsList = new ArrayList<Map<String, Object>>();
        cargoDetailsList.add(cargoDetailsMap);
        
        List<Map<String, Object>> contactInfoList = new ArrayList<Map<String, Object>>();
        Map<String, Object> contactInfoSendMap = new HashMap<>();
        contactInfoSendMap.put("contactType", 1);
        contactInfoSendMap.put("contact", "小曾");
        contactInfoSendMap.put("postCode", "580058");
        contactInfoSendMap.put("province", "北京市");
        contactInfoSendMap.put("city", "北京市");
        contactInfoSendMap.put("county", "通州区");
        contactInfoSendMap.put("address", "软件产业基地11栋");
        contactInfoSendMap.put("tel", "4006789888");
        contactInfoSendMap.put("country", "CN");
    	Map<String, Object> contactInfoReceiptMap = new HashMap<>();
    	contactInfoReceiptMap.put("contactType", 2);
    	contactInfoReceiptMap.put("company", "顺丰速运");
    	contactInfoReceiptMap.put("contact", "小邱");
    	contactInfoReceiptMap.put("tel", "15555542203");
    	contactInfoReceiptMap.put("province", "山西省");
    	contactInfoReceiptMap.put("city", "晋城市");
    	contactInfoReceiptMap.put("county", "高平市");
    	contactInfoReceiptMap.put("address", "湖北大厦");
    	contactInfoReceiptMap.put("postCode", "580058");
    	contactInfoReceiptMap.put("tel", "18810840728");
    	contactInfoReceiptMap.put("country", "CN");	
        contactInfoList.add(contactInfoSendMap);
        contactInfoList.add(contactInfoReceiptMap);
        
		paramItemsMap.put("language", "zh-CN");
		paramItemsMap.put("orderId", "jksdtestdev0002");
		paramItemsMap.put("cargoDetails", cargoDetailsList);
		paramItemsMap.put("contactInfoList", contactInfoList);
		Map<String, Object> paramMap = getBaseParam(serviceCode, paramItemsMap);
		
		String responseData = HttpRequest.post(requestUrl)
			    .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
			    .form(paramMap)
			    .execute().body();
        
    	System.out.println(responseData);
		return null;
	}
    
    private String getRequestUrl() {
    	String requestUrl = "https://sfapi-sbox.sf-express.com/std/service";
    	if(shunfengConfig.getIsProduct() == 0) {
    		requestUrl = "https://sfapi-sbox.sf-express.com/std/service";
    	}
    	return requestUrl;
    }
    
    private Map<String, Object> getBaseParam(String serviceCode,Map<String, Object> paramItemsMap){
    	Map<String, Object> paramMap = new HashMap<>();
    	String partnerID = shunfengConfig.getPartnerID();
        String requestID = shunfengConfig.getRequestID();
        Long timestamp = DateUtils.currentTimeMillis();
        String msgDigest = null;
        String checkWord = shunfengConfig.getCheckWord();
        paramMap.put("partnerID", partnerID);
		paramMap.put("requestID", requestID);
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
		msgDigest = Base64.getEncoder().encodeToString(DigestUtils.Md5(msgDigest));
		paramMap.put("msgDigest", msgDigest);
		return paramMap;
    }
    
    @Override
    public String getExpressCompanyCode() {
    	return ExpressCompanyCodeEnum.SF.getValue();
    }

}

