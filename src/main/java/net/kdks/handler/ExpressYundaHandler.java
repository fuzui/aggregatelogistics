package net.kdks.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpRequest;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;

/**
 * kuaidi110 logistics handler.
 * @ApplicationName: tnschool-server-upms-biz
 * @Title: Kuaidi110LogisticsHandler.java
 * @Package: cn.topnew.school.admin.handler.logistics
 * @author: wang ze
 * @date: 2020年9月18日 下午6:11:49
 */
public class ExpressYundaHandler implements ExpressHandler {

    @Override
    public ExpressResult getExpressInfo(ExpressParam expressParam) {
        String requestUrl = "https://devkyweixin.yundasys.com/interface_transite_search.php";
        String companyId = "e03bdcdef0524f0babeb32b324ccda27";
        String secretKey = "c8560cd55b36";
        
//        String contentTest = "{\"waybillNoList\":[\"773056265768441\"],\"order\":\"asc\"}";
        
        HashMap<String, Object> paramMap = new HashMap<>();
		String[] expressOn = {expressParam.getExpressNo()};
		paramMap.put("data", expressOn);
		paramMap.put("msg_type", "NEW_TRACES");
		paramMap.put("company_id", companyId);
		String beforeDigestStr = "company_id="+companyId+"&msg_type=NEW_TRACES&data=[\""+expressOn[0]+"\"]"+secretKey;
		System.out.println(beforeDigestStr);
		String dataDigest = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(beforeDigestStr.getBytes());
			dataDigest = Base64.getEncoder().encodeToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Map<String,String> requestHeader = new HashMap<String,String>();
		requestHeader.put("x-companyId", "");
		requestHeader.put("x-dataDigest", dataDigest);
		String responseData = HttpRequest.post(requestUrl)
			    .addHeaders(requestHeader)
			    .form(paramMap)
			    .execute().body();
		System.out.println(responseData);
        return null;
        
    }

    @Override
	public OrderResult createOrder() {
		// TODO Auto-generated method stub
		return null;
	}
    
    @Override
    public String getExpressCompanyCode() {
        return ExpressCompanyCodeEnum.YD.getValue();
    }

}

