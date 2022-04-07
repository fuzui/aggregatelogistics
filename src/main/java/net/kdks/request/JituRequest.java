package net.kdks.request;

import cn.hutool.http.HttpRequest;
import java.util.Base64;
import java.util.Map;
import net.kdks.config.JituConfig;
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
public class JituRequest {

    private String apiAccount;
    private String privateKey;

    private String uuid;

    private int isProduct;

    /**
     * Constructor.
     *
     * @param jituConfig 极兔配置
     */
    public JituRequest(JituConfig jituConfig) {
        this.apiAccount = jituConfig.getApiAccount();
        this.privateKey = jituConfig.getPrivateKey();
        this.isProduct = jituConfig.getIsProduct();
        this.uuid = jituConfig.getUuid();

    }

    /**
     * 获取请求地址.
     *
     * @param method 方法
     * @return 请求地址
     */
    public String getRequestUrl(String method) {
        if (isProduct == 0) {
            Assert.notEmpty(uuid, "请填写沙箱环境uuid！");
            Map<String, Object> param = MapUtils.newHashMap(1);
            param.put("uuid", uuid);
            return
                JituConstant.URL_TEST + method + "?" + StringUtils.buildMapToStrUrl(param, "UTF-8");
        }
        return JituConstant.URL + method;
    }

    /**
     * 路由查询.
     *
     * @param param  参数
     * @param format 格式
     * @return 查询结果
     */
    public String queryRouteRequest(String param, String format) {
        return request(JituMethod.QUERY_ROUTE, param, format);
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
     * 极兔通用请求.
     *
     * @param method 方法
     * @param param  参数
     * @param format 格式
     * @return 请求结果
     */
    public String request(String method, String param, String format) {
        Map<String, String> requestHeader = MapUtils.newHashMap(4);
        requestHeader.put("apiAccount", apiAccount);
        String timestamp = DateUtils.currentTimeStr();
        requestHeader.put("timestamp", timestamp);
        String digest =
            Base64.getEncoder().encodeToString(DigestUtils.md5Digest(param + privateKey));
        requestHeader.put("digest", digest);
        requestHeader.put("ContentType", "application/x-www-form-urlencoded; charset=utf-8");

        Map<String, Object> paramMap = MapUtils.newHashMap(1);
        paramMap.put("bizContent", param);
        return HttpRequest.post(getRequestUrl(method))
            .addHeaders(requestHeader)
            .body(StringUtils.buildMapToStrUrl(paramMap, "UTF-8"))
            .execute().body();
    }
}
