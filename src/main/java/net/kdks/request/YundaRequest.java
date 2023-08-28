package net.kdks.request;

import cn.hutool.http.HttpRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.kdks.config.YundaConfig;
import net.kdks.config.ZhongtongConfig;
import net.kdks.constant.YundaMethod;
import net.kdks.constant.ZhongtongMethod;
import net.kdks.constant.ZhongtongRouteVersion;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * <p>中通请求封装</p>
 * <p>date: 2021-01-12 13:29:57</p>.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
public class YundaRequest {

    private String appKey;

    private String appSecret;
    private String requestUrlPrefix;

    /**
     * Constructor.
     *
     * @param yundaConfig 韵达配置
     */
    public YundaRequest(YundaConfig yundaConfig) {
        this.appKey = yundaConfig.getAppKey();
        this.appSecret = yundaConfig.getAppSecret();
        this.requestUrlPrefix = YundaMethod.URL_PREFIX;
        if (yundaConfig.getIsProduct() == 0) {
            this.requestUrlPrefix = YundaMethod.URL_TEST_PREFIX;
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
        String requestUrl = requestUrlPrefix + YundaMethod.QUERY_ROUTE_URL;
        return request(requestUrl, param, format);
    }

    /**
     * 路由订阅.
     *
     * @param param 参数
     * @param format 格式
     * @return 查询结果
     */
    public String subscribeRouteRequest(String param, String format) {
        String requestUrl = requestUrlPrefix + YundaMethod.SUBSCRIBE_ROUTE_URL;
        return request(requestUrl, param, format);
    }

    /**
     * 运费预估.
     *
     * @param param 参数
     * @param format 格式
     * @return 请求结果
     */
    public String queryPriceRequest(String param, String format) {
        String requestUrl = requestUrlPrefix + YundaMethod.QUERY_PRICE_URL;
        return request(requestUrl, param, format);
    }

    /**
     * 通用请求.
     *
     * @param param 参数
     * @param format 格式
     * @return 请求结果
     */
    public String request(String requestUrl, String param, String format) {
        String beforeDigestStr = param + "_" + appSecret;
        String dataDigest = DigestUtils.md5DigestToStr(beforeDigestStr);
        Map<String, String> requestHeader = new HashMap<>(3);
        requestHeader.put("app-key", appKey);
        requestHeader.put("sign", dataDigest);
        requestHeader.put("req-time", Long.toString(DateUtils.currentTimeMillis()));
        requestHeader.put("Content-Type", "application/json; charset=utf-8");
        return HttpRequest.post(requestUrl)
            .addHeaders(requestHeader)
            .body(param)
            .execute().body();
    }

}
