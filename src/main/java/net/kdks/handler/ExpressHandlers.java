package net.kdks.handler;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import net.kdks.config.ExpressConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;

/**
 * express handler manager.
 * 
 * @author: wangze
 * @date: 2020年9月19日 下午6:15:18
 */
public class ExpressHandlers {

    /**
     * express handler container.
     */
    private final ConcurrentHashMap<String, ExpressHandler> expressHandlers = new ConcurrentHashMap<>(16);

    public ExpressHandlers(ExpressConfig expressConfig) {
        // Add all express handler
    	expressHandlers.put("STO", new ExpressShentongHandler(expressConfig.getShentongConfig()));
    	expressHandlers.put("YTO", new ExpressYuantongHandler(expressConfig.getYuantongConfig()));
    	expressHandlers.put("ZTO", new ExpressZhongtongHandler(expressConfig.getZhongtongConfig()));
    	expressHandlers.put("YD", new ExpressYundaHandler());
    	expressHandlers.put("HTKY", new ExpressBaishiHandler(expressConfig.getBaishiConfig()));
    	expressHandlers.put("SF", new ExpressShunfengHandler(expressConfig.getShunfengConfig()));
    }

    /**
     * get express info.
     *
     * @param expressParam expressParam must not be null
     * @param expressCompanyCode expressCompanyCode must not be null
     * @return express result
     */
    public ExpressResult getExpressInfo(ExpressParam expressParam, String expressCompanyCode) {
        return getSupportedCode(expressCompanyCode).getExpressInfo(expressParam);
    }
    
    public OrderResult createOrder(String expressCompanyCode) {
    	return getSupportedCode(expressCompanyCode).createOrder();
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
