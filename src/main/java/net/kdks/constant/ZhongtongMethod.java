package net.kdks.constant;

/**
 * 中通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ZhongtongMethod {
    /**
     * 路由查询方法名.
     */
    @Deprecated
    String QUERY_ROUTE_V1 = "NEW_TRACES";

    /**
     * 运费预估方法名.
     */
    @Deprecated
    String QUERY_PRICE = "GET_HOUR_PRICE";
    /**
     * 测试url前缀.
     */
    @Deprecated
    String URL_TEST_PREFIX_V1 = "https://japi-test.zto.com/";
    /**
     * 路由查询服务名.
     */
    @Deprecated
    String QUERY_ROUTE_URL_V1 = "traceInterfaceNewTraces";

    /**
     * url前缀.
     */
    String URL_PREFIX = "https://japi.zto.com/";
    /**
     * 测试url前缀.
     */
    String URL_TEST_PREFIX = "https://japi-test.zto.com/";
    /**
     * 路由查询服务名.
     */
    String QUERY_ROUTE_URL = "zto.merchant.waybill.track.query";
    /**
     * 运费查询服务名.
     */
    String QUERY_PRICE_URL = "zto.open.obtainPicePrescription";

}
