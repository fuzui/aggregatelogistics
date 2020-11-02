package net.kdks.handler;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import net.kdks.config.ExpressConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;

/**
 * 业务处理.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressHandlers {

    /**
     * 快递公司实例
     */
    private final ConcurrentHashMap<String, ExpressHandler> expressHandlers = new ConcurrentHashMap<>(16);

    public ExpressHandlers(ExpressConfig expressConfig) {
        // Add all express handler
    	expressHandlers.put("STO", new ExpressShentongHandler(expressConfig.getShentongConfig()));
    	expressHandlers.put("YTO", new ExpressYuantongHandler(expressConfig.getYuantongConfig()));
    	expressHandlers.put("ZTO", new ExpressZhongtongHandler(expressConfig.getZhongtongConfig()));
    	expressHandlers.put("HTKY", new ExpressBaishiHandler(expressConfig.getBaishiConfig()));
    	expressHandlers.put("SF", new ExpressShunfengHandler(expressConfig.getShunfengConfig()));
    }

    /**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @param expressCompanyCode	快递公司编码
     * @return 查询接口
     */
    public ExpressResult getExpressInfo(ExpressParam expressParam, String expressCompanyCode) {
        return getSupportedCode(expressCompanyCode).getExpressInfo(expressParam);
    }
    /**
     * 创建订单
     * @param createOrderParam	下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @param expressCompanyCode	快递公司编码
     * @return	快递单号等信息
     */
    public OrderResult createOrder(CreateOrderParam createOrderParam, String expressCompanyCode) {
    	return getSupportedCode(expressCompanyCode).createOrder(createOrderParam);
    }

    /**
     * Adds express handlers.
     *
     * @param expressHandlers express handler collection
     * @return current express handlers
     */
    public ExpressHandlers addLogisticsHandlers(Collection<ExpressHandler> expressHandlers) {
        if (expressHandlers == null || expressHandlers.isEmpty()) {
            for (ExpressHandler handler : expressHandlers) {
                if (this.expressHandlers.containsKey(handler.getExpressCompanyCode())) {
                    System.out.println("Same express company code implements must be unique");
                }
                this.expressHandlers.put(handler.getExpressCompanyCode(), handler);
            }
        }
        return this;
    }
    
    /**
     * get supported code
     * @param code express company code
     * @return
     * @return ExpressHandler
     */
    private ExpressHandler getSupportedCode(String code)  {
    	ExpressHandler handler = expressHandlers.getOrDefault(code, expressHandlers.get(ExpressCompanyCodeEnum.STO.getValue()));
    	if(handler == null) {
        	
        }
        return handler;
    }
}
