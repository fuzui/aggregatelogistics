package net.kdks.constant;

/**
 * 圆通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface YuantongMethod {
    /**
     * 路由查询.
     */
    String QUERY_ROUTE = "yto.Marketing.WaybillTrace";
    /**
     * 运费预估.
     */
    String QUERY_PRICE = "yto.Marketing.TransportPrice";

    String URL_PREFIX = "http://openapi.yto.net.cn/service/";

    String URL_TEST_PREFIX = "http://opentestapi.yto.net.cn/service/";

    String URL_VERSION = "/v1/";

    String QUERY_ROUTE_URL = "waybill_query";

    String QUERY_PRICE_URL = "charge_query";


}
