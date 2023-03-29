package net.kdks.request;

import cn.hutool.http.HttpRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.kdks.config.ZhongtongConfig;
import net.kdks.constant.ZhongtongMethod;
import net.kdks.constant.ZhongtongRouteVersion;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * <p>中通请求封装</p>
 * <p>date: 2021-01-12 13:29:57</p>.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
public class ZhongtongRequest {

    private String companyId;
    private String secretKeyV1;

    private String appKey;

    private String secretKey;
    private String requestUrlPrefix;

    /**
     * Constructor.
     *
     * @param zhongtongConfig 中通配置
     */
    public ZhongtongRequest(ZhongtongConfig zhongtongConfig) {
        if (ZhongtongRouteVersion.V1.equals(zhongtongConfig.getRouteVersion())) {
            this.companyId = zhongtongConfig.getCompanyId();
            this.secretKeyV1 = zhongtongConfig.getSecretKeyV1();
        } else {
            this.appKey = zhongtongConfig.getAppKey();
            this.secretKey = zhongtongConfig.getSecretKey();
        }
        this.requestUrlPrefix = ZhongtongMethod.URL_PREFIX;
        if (zhongtongConfig.getIsProduct() == 0) {
            this.requestUrlPrefix = ZhongtongMethod.URL_TEST_PREFIX;
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
        String requestUrl = requestUrlPrefix + ZhongtongMethod.QUERY_ROUTE_URL;
        return request(requestUrl, param, format);
    }

    /**
     * 路由查询v1.
     *
     * @param param 参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryRouteRequestV1(String param, String format) {
        String requestUrl = requestUrlPrefix + ZhongtongMethod.QUERY_ROUTE_URL_V1;
        return requestV1(ZhongtongMethod.QUERY_ROUTE_V1, requestUrl, param, format);
    }

    /**
     * 运费预估.
     *
     * @param param 参数
     * @param format 格式
     * @return 请求结果
     */
    public String queryPriceRequest(String param, String format) {
        String requestUrl = requestUrlPrefix + ZhongtongMethod.QUERY_PRICE_URL;
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
        String beforeDigestStr = param + secretKey;
        String dataDigest =
            Base64.getEncoder().encodeToString(DigestUtils.md5Digest(beforeDigestStr));
        Map<String, String> requestHeader = new HashMap<>(3);
        requestHeader.put("x-appKey", appKey);
        requestHeader.put("x-dataDigest", dataDigest);
        requestHeader.put("Content-Type", "application/json; charset=utf-8");
        return HttpRequest.post(requestUrl)
            .addHeaders(requestHeader)
            .body(param)
            .execute().body();
    }

    /**
     * 通用请求v1.
     *
     * @param method 方法
     * @param param 参数
     * @param format 格式
     * @return 请求结果
     */
    public String requestV1(String method, String requestUrl, String param, String format) {
        HashMap<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("company_id", companyId);
        paramMap.put("msg_type", method);
        paramMap.put("data", param);
        String beforeDigestStr = StringUtils.buildMapToStr(paramMap, "UTF-8") + secretKeyV1;
        String dataDigest =
            Base64.getEncoder().encodeToString(DigestUtils.md5Digest(beforeDigestStr));
        Map<String, String> requestHeader = new HashMap<>(3);
        requestHeader.put("x-companyid", companyId);
        requestHeader.put("x-datadigest", dataDigest);
        requestHeader.put("ContentType", "application/x-www-form-urlencoded; charset=utf-8");
        return HttpRequest.post(requestUrl)
            .addHeaders(requestHeader)
            .body(StringUtils.buildMapToStrUrl(paramMap, "UTF-8"))
            .execute().body();
    }
}
