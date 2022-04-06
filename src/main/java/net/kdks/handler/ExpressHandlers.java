package net.kdks.handler;

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
        expressHandlers.put("STO", new ExpressShentongHandler(expressConfig.getShentongConfig()));
        expressHandlers.put("YTO", new ExpressYuantongHandler(expressConfig.getYuantongConfig()));
        expressHandlers.put("ZTO", new ExpressZhongtongHandler(expressConfig.getZhongtongConfig()));
        expressHandlers.put("HTKY", new ExpressBaishiHandler(expressConfig.getBaishiConfig()));
        expressHandlers.put("SF", new ExpressShunfengHandler(expressConfig.getShunfengConfig()));
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
     * get supported code.
     *
     * @param code express company code
     * @return ExpressHandler
     */
    private ExpressHandler getSupportedCode(String code) {
        ExpressHandler handler = expressHandlers.getOrDefault(code,
            expressHandlers.get(ExpressCompanyCodeEnum.STO.getValue()));
        return handler;
    }
}
