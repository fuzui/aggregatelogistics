package net.kdks.constant;

/**
 * 圆通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ShentongMethod {

    String URL = "https://cloudinter-linkgatewayonline.sto.cn/gateway/link.do";

    String URL_TEST = "http://cloudinter-linkgatewaytest.sto.cn/gateway/link.do";

    /**
     * 路由查询.
     */
    String QUERY_ROUTE_API_NAME = "STO_TRACE_QUERY_COMMON";

    String QUERY_ROUTE_TO_APPKEY = "sto_trace_query";

    String QUERY_ROUTE_TO_CODE = "sto_trace_query";
    /**
     * 运费预估.
     */
    String QUERY_PRICE_API_NAME = "QUERY_SEND_SERVICE_DETAIL";

    String QUERY_PRICE_TO_APPKEY = "ORDERMS_API";

    String QUERY_PRICE_TO_CODE = "ORDERMS_API";

}
