package net.kdks.handler;

import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.kdks.config.ZhongtongConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.CreateOrderParam;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressPriceParam;
import net.kdks.model.ExpressPriceResult;
import net.kdks.model.ExpressResponse;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.zto.ZhongtongData;
import net.kdks.model.zto.ZhongtongResult;
import net.kdks.model.zto.ZhongtongTrace;
import net.kdks.model.zto.price.ZhongtongPriceResult;
import net.kdks.request.ZhongtongRequest;
import net.kdks.utils.MapUtils;

/**
 * 中通.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressZhongtongHandler implements ExpressHandler {

    private ZhongtongRequest zhongtongRequest;

    public ExpressZhongtongHandler(ZhongtongConfig zhongtongConfig) {
        this.zhongtongRequest = new ZhongtongRequest(zhongtongConfig);
    }

    /**
     * 查询轨迹信息.
     *
     * @param expressParam 快递号、手机、快递公司编码
     * @return 查询接口
     */
    @Override
    public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam) {
        List<String> expressNos = expressParam.getExpressNos();
        String param = JSON.toJSONString(expressNos);
        String responseData = zhongtongRequest.queryRouteRequest(param, expressParam.getFormat());
        return disposeResult(responseData, expressParam);
    }

    /**
     * 结果处理.
     *
     * @param responseData 响应
     * @return 处理结果
     */
    private ExpressResponse<List<ExpressResult>> disposeResult(String responseData,
                                                               ExpressParam expressParam) {
        List<String> expressNos = expressParam.getExpressNos();
        ZhongtongResult result = JSON.parseObject(responseData, ZhongtongResult.class);
        List<ExpressResult> expressResults = new ArrayList<>();
        if (result.getStatus()) {
            List<ZhongtongData> zhongtongDatas = result.getData();
            if (zhongtongDatas == null || zhongtongDatas.size() == 0) {
                return ExpressResponse.failed(CommonConstant.NO_INFO);
            }
            Map<String, ZhongtongData> zhongtongDataMap =
                MapUtils.newHashMap(zhongtongDatas.size());
            for (ZhongtongData zhongtongData : zhongtongDatas) {
                zhongtongDataMap.put(zhongtongData.getBillCode(), zhongtongData);
            }
            for (String expressNo : expressNos) {
                ExpressResult expressResult = new ExpressResult();
                if (expressParam.isViewOriginal()) {
                    expressResult.setOriginalResult(responseData);
                }
                expressResult.setCom(ExpressCompanyCodeEnum.ZTO.getValue());
                expressResult.setNu(expressNo);
                List<ZhongtongTrace> routes = zhongtongDataMap.get(expressNo).getTraces();
                if (routes == null || routes.size() == 0) {
                    expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
                    expressResult.setMsg(CommonConstant.NO_INFO);
                    expressResults.add(expressResult);
                    continue;
                }
                //官方默认正序，改为倒序
                Collections.reverse(routes);
                ExpressData latestData = routes.get(0);
                if (expressParam.isViewRoute()) {
                    List<ExpressData> data = new ArrayList<>(routes.size());
                    data.addAll(routes);
                    expressResult.setData(data);
                }
                expressResult.setState(latestData.getStatus());
                if (ExpressStateEnum.SIGNED.getValue().equals(expressResult.getState())) {
                    expressResult.setIscheck(CommonConstant.YES);
                }
                expressResults.add(expressResult);
            }
            return ExpressResponse.ok(expressResults);
        }
        return ExpressResponse.failed(result.getMessage());
    }

    /**
     * 运费预估.
     *
     * @param expressPriceParam 起始省份、起始城市、目的身份、目的城市、重量、长、宽、高
     * @return 运费
     */
    @Override
    public ExpressResponse<ExpressPriceResult> getExpressPrice(
        ExpressPriceParam expressPriceParam) {
        Map<String, Object> param = MapUtils.newHashMap(4);
        param.put("sendProv", expressPriceParam.getStartProvince());
        param.put("sendCity", expressPriceParam.getStartProvince());
        param.put("dispProv", expressPriceParam.getEndProvince());
        param.put("dispCity", expressPriceParam.getEndProvince());
        String responseData =
            zhongtongRequest.queryPriceRequest(JSON.toJSONString(param),
                expressPriceParam.getFormat());
        ZhongtongPriceResult zhongtongPriceResult =
            JSON.parseObject(responseData, ZhongtongPriceResult.class);
        if (!zhongtongPriceResult.getStatus()) {
            return ExpressResponse.failed(zhongtongPriceResult.getMsg());
        }
        ExpressPriceResult expressPriceResult = new ExpressPriceResult();
        BigDecimal addMoney =
            new BigDecimal(zhongtongPriceResult.getData().getAddMoney()).setScale(2);
        BigDecimal firstMoney =
            new BigDecimal(zhongtongPriceResult.getData().getFirstMoney()).setScale(2);
        BigDecimal standardWeight = new BigDecimal("1.00");
        BigDecimal price = firstMoney;
        BigDecimal weight = expressPriceParam.getWeight();
        if (weight.compareTo(standardWeight) == 1) {
            price = price.add(
                weight.subtract(standardWeight).divide(standardWeight, 0, RoundingMode.CEILING)
                    .multiply(addMoney).setScale(2));
        }
        expressPriceResult.setPrice(price);
        expressPriceResult.setTime(
            new BigDecimal(zhongtongPriceResult.getData().getHour()).setScale(2));
        return ExpressResponse.ok(expressPriceResult);
    }

    /**
     * 创建订单.
     *
     * @param createOrderParam 下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @return 快递单号等信息
     */
    @Override
    public ExpressResponse<OrderResult> createOrder(CreateOrderParam createOrderParam) {
        return ExpressResponse.failed(CommonConstant.NO_SOPPORT);
    }

    /**
     * 获取当前快递公司编码.
     *
     * @return 快递公司编码
     */
    @Override
    public String getExpressCompanyCode() {
        return ExpressCompanyCodeEnum.ZTO.getValue();
    }

}

