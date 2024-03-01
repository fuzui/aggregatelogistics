package net.kdks.constant;

/**
 * 京东路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.11
 */
public interface JingdongMethod {
    /**
     * 路由查询方法名.
     */
    String QUERY_ROUTE = "/ecap/v1/orders/trace/query";

    /**
     * 运费预估方法名.
     */
    String QUERY_PRICE = "/ecap/v1/orders/actualfee/query";

}
