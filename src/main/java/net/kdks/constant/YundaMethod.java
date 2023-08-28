package net.kdks.constant;

/**
 * 韵达接口方法.
 *
 * @author Ze.Wang
 * @since 0.0.9
 */
public interface YundaMethod {
    /**
     * url前缀.
     */
    String URL_PREFIX = "https://openapi.yundaex.com/";
    /**
     * 测试url前缀.
     */
    String URL_TEST_PREFIX = "https://u-openapi.yundasys.com/";
    /**
     * 路由查询后缀.
     */
    String QUERY_ROUTE_URL = "openapi/outer/logictis/query";

    /**
     * 路由订阅后缀.
     */
    String SUBSCRIBE_ROUTE_URL = "openapi/outer/logictis/subscribe";

    /**
     * 运费查询服务名.
     */
    String QUERY_PRICE_URL = "openapi-api/v1/order/getFreightInfo";

}
