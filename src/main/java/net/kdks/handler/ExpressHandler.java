package net.kdks.handler;

import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;

/**
 * express handler interface.
 * 
 * @author: wangze
 * @date: 2020年9月19日 下午6:10:13
 */
public interface ExpressHandler {

    /**
     * get express info
     * 
     * @param logisticsParam
     * @return
     */
    ExpressResult getExpressInfo(ExpressParam expressParam);
    /**
     * 创建订单
     * @return
     */
    OrderResult createOrder();

    /**
     * Get express company code is supported.
     *
     * @return express code
     */
    String  getExpressCompanyCode();
}