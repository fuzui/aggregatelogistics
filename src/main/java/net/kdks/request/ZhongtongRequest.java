package net.kdks.request;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.http.HttpRequest;
import net.kdks.config.ZhongtongConfig;
import net.kdks.constant.ZhongtongMethod;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * <p>中通请求封装</p>
 * <p>date: 2021-01-12 13:29:57</p>
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
public class ZhongtongRequest {
	
	private String companyId;
	private String secretKey;
	private String requestUrlPrefix;
	public ZhongtongRequest(ZhongtongConfig zhongtongConfig) {
		this.companyId = zhongtongConfig.getCompanyId();
		this.secretKey = zhongtongConfig.getSecretKey();
		this.requestUrlPrefix = ZhongtongMethod.URL_PREFIX;
    	if(zhongtongConfig.getIsProduct() == 0) {
    		this.requestUrlPrefix = ZhongtongMethod.URL_TEST_PREFIX;
    		companyId = "kfpttestCode";
            secretKey = "kfpttestkey==";
		}
	}
	/**
	 * 路由查询
	 * @param param
	 * @param format
	 * @return
	 */
	public String queryRouteRequest(String param,String format) {
		String requestUrl = requestUrlPrefix+ZhongtongMethod.QUERY_ROUTE_URL;
		return request(ZhongtongMethod.QUERY_ROUTE, requestUrl, param, format);
	}
	/**
	 * 运费预估
	 * @param param
	 * @param format
	 * @return
	 */
	public String queryPriceRequest(String param,String format) {
		String requestUrl = requestUrlPrefix+ZhongtongMethod.QUERY_PRICE_UR;
		return request(ZhongtongMethod.QUERY_PRICE, requestUrl, param, format);
	}
	
	/**
	 * 通用请求
	 * @param method
	 * @param param
	 * @param format
	 * @return
	 */
	public String request(String method, String requestUrl, String param,String format) {
		HashMap<String, Object> paramMap = new HashMap<>(3);
		paramMap.put("company_id", companyId);
		paramMap.put("msg_type", method);
		paramMap.put("data", param);
		
		String beforeDigestStr = StringUtils.buildMapToStr(paramMap, "UTF-8")+secretKey;
		String dataDigest = Base64.getEncoder().encodeToString(DigestUtils.md5Digest(beforeDigestStr));
		Map<String,String> requestHeader = new HashMap<String,String>(3);
		requestHeader.put("x-companyid", companyId);
		requestHeader.put("x-datadigest", dataDigest);
		requestHeader.put("ContentType", "application/x-www-form-urlencoded; charset=utf-8");
		String responseData = HttpRequest.post(requestUrl)
			    .addHeaders(requestHeader)
			    .body(StringUtils.buildMapToStrUrl(paramMap, "UTF-8"))
			    .execute().body();
		return responseData;
	}
}
