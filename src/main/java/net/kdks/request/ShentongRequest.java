package net.kdks.request;

import cn.hutool.http.HttpUtil;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.kdks.config.ShentongConfig;
import net.kdks.constant.ShentongMethod;
import net.kdks.utils.DigestUtils;

/**
 * <p>申通请求封装</p>
 * <p>date: 2021-01-13 17:23:55</p>.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
public class ShentongRequest {

    private String secretKey;
    private String appkey;
    private String code;

    private String requestUrl;

    /**
     * Constructor.
     *
     * @param shentongConfig 申通配置
     */
    public ShentongRequest(ShentongConfig shentongConfig) {
        this.secretKey = shentongConfig.getSecretKey();
        this.appkey = shentongConfig.getAppkey();
        this.code = shentongConfig.getAppkey();
        this.requestUrl = ShentongMethod.URL;
        if (shentongConfig.getIsProduct() == 0) {
            this.requestUrl = ShentongMethod.URL_TEST;
        }
    }

    /**
     * 路由查询.
     *
     * @param param 参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryRouteRequest(String param, String format) {
        return request(ShentongMethod.QUERY_ROUTE_API_NAME,
            ShentongMethod.QUERY_ROUTE_TO_APPKEY,
            ShentongMethod.QUERY_ROUTE_TO_CODE,
            param,
            format);
    }

    /**
     * 运费预估.
     *
     * @param param 参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryPriceRequest(String param, String format) {
        return request(ShentongMethod.QUERY_PRICE_API_NAME,
            ShentongMethod.QUERY_PRICE_TO_APPKEY,
            ShentongMethod.QUERY_PRICE_TO_CODE,
            param,
            format);
    }

    /**
     * 申通通用请求.
     *
     * @param method 方法
     * @param param 参数
     * @param format 格式
     * @return 请求结果
     */
    public String request(String method, String toAppkey, String toCode, String param,
                          String format) {
        Map<String, Object> paramMap = new HashMap<>(7);
        paramMap.put("content", param);
        paramMap.put("api_name", method);
        paramMap.put("from_appkey", appkey);
        paramMap.put("from_code", code);
        paramMap.put("to_appkey", toAppkey);
        paramMap.put("to_code", toCode);
        String beforeDigestStr = param + secretKey;
        String dataDigest =
            Base64.getEncoder().encodeToString(DigestUtils.md5Digest(beforeDigestStr));
        paramMap.put("data_digest", dataDigest);

        return HttpUtil.post(requestUrl, paramMap);
    }
}
