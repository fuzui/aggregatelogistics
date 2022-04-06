package net.kdks.constant;

/**
 * 圆通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ZhongtongMethod {
    /**
     * 路由查询方法名.
     */
    String QUERY_ROUTE = "NEW_TRACES";

    /**
     * 运费预估方法名.
     */
    String QUERY_PRICE = "GET_HOUR_PRICE";
    /**
     * url前缀.
     */
    String URL_PREFIX = "http://japi.zto.cn/";
    /**
     * 测试url前缀.
     */
    String URL_TEST_PREFIX = "https://japi-test.zto.com/";
    /**
     * url前缀.
     */
    String URL_PREFIX_V2 = "https://japi.zto.com/";
    /**
     * 测试url前缀.
     */
    String URL_TEST_PREFIX_V2 = "https://japi-test.zto.com/";
    /**
     * 路由查询服务名.
     */
    String QUERY_ROUTE_URL = "traceInterfaceNewTraces";
    /**
     * 运费查询服务名.
     */
    String QUERY_PRICE_UR = "priceAndHourInterfaceGetHourPrice";

    String QUERY_PRICE_URL_V2 = "zto.open.obtainPicePrescription";

}
