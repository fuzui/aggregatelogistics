package net.kdks.handler;

import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;

/**
 * 业务处理接口.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ExpressHandler {

    /**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
    ExpressResult getExpressInfo(ExpressParam expressParam);

    /**
     * 创建订单
     * @param createOrderParam	下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @return	快递单号等信息
     */
    OrderResult createOrder(CreateOrderParam createOrderParam);

    /**
     * 获取当前快递公司编码
     * @return 快递公司编码
     */
    String  getExpressCompanyCode();
}