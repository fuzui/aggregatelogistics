package net.kdks.request;

import cn.hutool.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.kdks.config.JingdongConfig;
import net.kdks.config.JituConfig;
import net.kdks.constant.JingdongConstant;
import net.kdks.constant.JingdongMethod;
import net.kdks.constant.JituConstant;
import net.kdks.constant.JituMethod;
import net.kdks.utils.Assert;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.MapUtils;
import net.kdks.utils.StringUtils;

/**
 * <p>极兔请求封装</p>
 * <p>date: 2021-01-13 17:23:55</p>.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
public class JingdongRequest {

    private String appKey;
    private String appSecret;

    private String accessToken;

    private int isProduct;

    /**
     * Constructor.
     *
     * @param jingdongConfig 京东配置
     */
    public JingdongRequest(JingdongConfig jingdongConfig) {
        this.appKey = jingdongConfig.getAppKey();
        this.appSecret = jingdongConfig.getAppSecret();
        this.accessToken = jingdongConfig.getAccessToken();
        this.isProduct = jingdongConfig.getIsProduct();
    }

    /**
     * 获取请求地址.
     *
     * @return 请求地址
     */
    public String getRequestUrl() {
        if (isProduct == 0) {
            return
                JingdongConstant.URL_TEST;
        }
        return JingdongConstant.URL;
    }

    /**
     * 路由查询.
     *
     * @param param  参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryRouteRequest(String param, String format) {
        return request(JingdongMethod.QUERY_ROUTE, param, format);
    }

    /**
     * 运费预估.
     *
     * @param param  参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryPriceRequest(String param, String format) {
        return request(JituMethod.QUERY_PRICE, param, format);
    }

    /**
     * 京东兔通用请求.
     *
     * @param method 方法
     * @param param  参数
     * @param format 格式
     * @return 请求结果
     */
    @SuppressWarnings("checkstyle:LocalVariableName")
    public String request(String method, String param, String format) {
        Map<String, Object> query = MapUtils.newHashMap(7);;
        query.put("LOP-DN", "ECAP");
        query.put("app_key", appKey);
        query.put("access_token", accessToken);
        String timestamp = DateUtils.currentTimeStr();
        query.put("timestamp", timestamp);
        query.put("v", "2.0");
        String content = String.join("", new String[]{
            appSecret,
            "access_token", accessToken,
            "app_key", appKey,
            "method", method,
            "param_json", param,
            "timestamp", timestamp,
            "v", "2.0",
            appSecret});

        String sign = DigestUtils.md5DigestToStr(content);
        query.put("sign", sign);
        query.put("algorithm", "md5-salt");
        String url = getRequestUrl() + method + "?"
            + StringUtils.buildMapToStrUrl(query, "UTF-8");
        Map<String, String> requestHeader = MapUtils.newHashMap(1);;
        requestHeader.put("ContentType", "application/json; charset=utf-8");

        return HttpRequest.post(url)
            .addHeaders(requestHeader)
            .body(param)
            .execute().body();
    }
}
