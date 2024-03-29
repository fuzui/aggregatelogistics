package net.kdks.request;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import net.kdks.config.YuantongConfig;
import net.kdks.constant.YuantongMethod;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * <p>圆通请求封装</p>
 * <p>date: 2021-01-12 13:29:57</p>.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
public class YuantongRequest {

    private String secretKey;
    private String appKey;
    private String userId;
    private String requestUrlPrefix;
    private String version = "1";

    /**
     * Constructor.
     *
     * @param yuantongConfig 圆通配置
     */
    public YuantongRequest(YuantongConfig yuantongConfig) {
        this.secretKey = yuantongConfig.getSecretKey();
        this.appKey = yuantongConfig.getAppkey();
        this.userId = yuantongConfig.getUserId();
        this.requestUrlPrefix = YuantongMethod.URL_PREFIX;
        if (yuantongConfig.getIsProduct() == 0) {
            this.requestUrlPrefix = YuantongMethod.URL_TEST_PREFIX;
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
        String requestUrl =
            requestUrlPrefix + YuantongMethod.QUERY_ROUTE_URL + YuantongMethod.URL_VERSION + userId;
        return request(YuantongMethod.QUERY_ROUTE, requestUrl, param, format);
    }

    /**
     * 运费预估.
     *
     * @param param 参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryPriceRequest(String param, String format) {
        String requestUrl =
            requestUrlPrefix + YuantongMethod.QUERY_PRICE_URL + YuantongMethod.URL_VERSION + userId;
        return request(YuantongMethod.QUERY_PRICE, requestUrl, param, format);
    }

    /**
     * 圆通通用请求.
     *
     * @param method 方法
     * @param param 参数
     * @param format 格式
     * @return 请求结果
     */
    public String request(String method, String requestUrl, String param, String format) {
        Map<String, Object> paramMap = new HashMap<>(8);
        String timestamp = DateUtils.currentTimeStr();
        paramMap.put("app_key", appKey);
        paramMap.put("format", format);
        paramMap.put("method", method);
        paramMap.put("timestamp", timestamp);
        paramMap.put("user_id", userId);
        paramMap.put("v", version);
        paramMap.put("param", param);
        StringBuilder beforeDigestStr = new StringBuilder(secretKey);
        beforeDigestStr.append("app_key").append(appKey)
            .append("format").append(format)
            .append("method").append(method)
            .append("timestamp").append(timestamp)
            .append("user_id").append(userId)
            .append("v").append(version);
        String dataDigest = StringUtils.strTo16(DigestUtils.md5Digest(beforeDigestStr.toString()));
        paramMap.put("sign", dataDigest.toUpperCase());

        return HttpRequest.post(requestUrl)
            .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
            .body(StringUtils.buildMapToStr(paramMap, "UTF-8"))
            .execute().body();
    }
}
