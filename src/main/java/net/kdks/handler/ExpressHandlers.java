package net.kdks.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import net.kdks.config.ExpressConfig;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressPriceParam;
import net.kdks.model.ExpressPriceResult;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.utils.Assert;

/**
 * 业务处理.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressHandlers {

    /**
     * 快递公司实例.
     */
    private final ConcurrentHashMap<String, ExpressHandler> expressHandlers =
        new ConcurrentHashMap<>(16);

    /**
     * Add all express handler.
     *
     * @param expressConfig 快递配置
     */
    public ExpressHandlers(ExpressConfig expressConfig) {
        List<ExpressHandler> expressHandlerList = new ArrayList<>();
        if (expressConfig.getShentongConfig() != null) {
            expressHandlerList.add(new ExpressShentongHandler(expressConfig.getShentongConfig()));
        }
        if (expressConfig.getYuantongConfig() != null) {
            expressHandlerList.add(new ExpressYuantongHandler(expressConfig.getYuantongConfig()));
        }
        if (expressConfig.getZhongtongConfig() != null) {
            expressHandlerList.add(new ExpressZhongtongHandler(expressConfig.getZhongtongConfig()));
        }
        if (expressConfig.getBaishiConfig() != null) {
            expressHandlerList.add(new ExpressBaishiHandler(expressConfig.getBaishiConfig()));
        }
        if (expressConfig.getShunfengConfig() != null) {
            expressHandlerList.add(new ExpressShunfengHandler(expressConfig.getShunfengConfig()));
        }
        if (expressConfig.getJituConfig() != null) {
            expressHandlerList.add(new ExpressJituHandler(expressConfig.getJituConfig()));
        }
        if (expressConfig.getYundaConfig() != null) {
            expressHandlerList.add(new ExpressYundaHandler(expressConfig.getYundaConfig()));
        }
        addLogisticsHandlers(expressHandlerList);
    }

    /**
     * 查询轨迹信息.
     *
     * @param expressParam       快递号、手机、快递公司编码
     * @param expressCompanyCode 快递公司编码
     * @return 查询接口
     */
    public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam,
                                                               String expressCompanyCode) {
        return getSupportedCode(expressCompanyCode).getExpressInfo(expressParam);
    }

    /**
     * 运费预估.
     *
     * @param expressPriceParam 起始省份、起始城市、目的身份、目的城市、重量、长、宽、高
     * @return 运费
     */
    public ExpressResponse<ExpressPriceResult> getExpressPrice(ExpressPriceParam expressPriceParam,
                                                               String expressCompanyCode) {
        return getSupportedCode(expressCompanyCode).getExpressPrice(expressPriceParam);
    }

    /**
     * 创建订单.
     *
     * @param createOrderParam   下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @param expressCompanyCode 快递公司编码
     * @return 快递单号等信息
     */
    public ExpressResponse<OrderResult> createOrder(CreateOrderParam createOrderParam,
                                                    String expressCompanyCode) {
        return getSupportedCode(expressCompanyCode).createOrder(createOrderParam);
    }

    /**
     * Adds express handlers.
     *
     * @param expressHandlers express handler collection
     * @return current express handlers
     */
    public ExpressHandlers addLogisticsHandlers(Collection<ExpressHandler> expressHandlers) {
        if (expressHandlers != null && expressHandlers.size() > 0) {
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
     * get supported code.
     *
     * @param code express company code
     * @return ExpressHandler
     */
    private ExpressHandler getSupportedCode(String code) {
        ExpressHandler handler = expressHandlers.getOrDefault(code,
            expressHandlers.get(ExpressCompanyCodeEnum.STO.getValue()));
        Assert.notNull(handler, "不支持的快递公司！");
        return handler;
    }
}
