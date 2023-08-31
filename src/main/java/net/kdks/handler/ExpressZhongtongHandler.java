package net.kdks.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.kdks.config.ZhongtongConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.constant.ZhongtongRouteVersion;
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
import net.kdks.model.zto.ZhongtongResultV1;
import net.kdks.model.zto.ZhongtongTrace;
import net.kdks.model.zto.ZhongtongTraceV1;
import net.kdks.model.zto.price.ZhongtongPriceData;
import net.kdks.request.ZhongtongRequest;
import net.kdks.utils.Assert;
import net.kdks.utils.MapUtils;

/**
 * 中通.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressZhongtongHandler implements ExpressHandler {

    private ZhongtongRequest zhongtongRequest;

    private ZhongtongConfig zhongtongConfig;

    public ExpressZhongtongHandler(ZhongtongConfig zhongtongConfig) {
        this.zhongtongConfig = zhongtongConfig;
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
        if (ZhongtongRouteVersion.V1.equals(this.zhongtongConfig.getRouteVersion())) {
            return getExpressInfoV1(expressParam);
        }
        List<ExpressResult> expressResults = new ArrayList<>();
        for (String expressNo : expressParam.getExpressNos()) {
            Map<String, Object> param = MapUtils.newHashMap(2);
            param.put("billCode", expressNo);
            param.put("mobilePhone", expressParam.getMobile());
            String responseData = zhongtongRequest.queryRouteRequest(JSON.toJSONString(param),
                expressParam.getFormat());
            ExpressResult expressResult = disposeResult(responseData, expressNo, expressParam);
            expressResults.add(expressResult);
        }
        return ExpressResponse.ok(expressResults);
    }

    /**
     * 结果处理.
     *
     * @param responseData 响应
     * @return 处理结果
     */
    private ExpressResult disposeResult(String responseData, String expressNo,
                                        ExpressParam expressParam) {
        ExpressResult expressResult = new ExpressResult();
        if (expressParam.isViewOriginal()) {
            expressResult.setOriginalResult(responseData);
        }
        expressResult.setCom(ExpressCompanyCodeEnum.ZTO.getValue());
        expressResult.setNu(expressNo);
        List<String> expressNos = expressParam.getExpressNos();
        ZhongtongResult<List<ZhongtongTrace>> result = JSON.parseObject(responseData,
            new TypeReference<ZhongtongResult<List<ZhongtongTrace>>>() {
            });
        if (!result.getStatus()) {
            expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
            expressResult.setMsg(result.getMessage());
            return expressResult;
        }
        if (result.getResult() == null && result.getResult().size() == 0) {
            expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
            expressResult.setMsg(CommonConstant.NO_INFO);
            return expressResult;
        }
        List<ZhongtongTrace> routes = result.getResult();
        // 默认正序，改为倒序
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
        return expressResult;
    }

    /**
     * 查询轨迹信息v1.
     *
     * @param expressParam 快递号、手机、快递公司编码
     * @return 查询接口
     */
    public ExpressResponse<List<ExpressResult>> getExpressInfoV1(ExpressParam expressParam) {
        List<String> expressNos = expressParam.getExpressNos();
        String param = JSON.toJSONString(expressNos);
        String responseData = zhongtongRequest.queryRouteRequestV1(param, expressParam.getFormat());
        return disposeResultV1(responseData, expressParam);
    }

    /**
     * 结果处理.
     *
     * @param responseData 响应
     * @return 处理结果
     */
    private ExpressResponse<List<ExpressResult>> disposeResultV1(String responseData,
                                                                 ExpressParam expressParam) {
        List<String> expressNos = expressParam.getExpressNos();
        ZhongtongResultV1 result = JSON.parseObject(responseData, ZhongtongResultV1.class);
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
                List<ZhongtongTraceV1> routes = zhongtongDataMap.get(expressNo).getTraces();
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
        Map<String, String> sender = MapUtils.newHashMap(4);
        sender.put("province", expressPriceParam.getStartProvince());
        sender.put("city", expressPriceParam.getStartCity());
        sender.put("district", expressPriceParam.getStartDistrict());
        sender.put("address", expressPriceParam.getStartAddress());
        Map<String, String> addresser = MapUtils.newHashMap(4);
        addresser.put("province", expressPriceParam.getEndProvince());
        addresser.put("city", expressPriceParam.getEndCity());
        addresser.put("district", expressPriceParam.getEndDistrict());
        addresser.put("address", expressPriceParam.getEndAddress());
        Map<String, Object> param = MapUtils.newHashMap(4);
        param.put("transportType", 1);
        param.put("sender", sender);
        param.put("addresser", addresser);
        Assert.notNull(expressPriceParam.getWeight(), "请填写重量！");
        param.put("weight", expressPriceParam.getWeight().setScale(2, BigDecimal.ROUND_HALF_UP));
        String responseData =
            zhongtongRequest.queryPriceRequest(JSON.toJSONString(param),
                expressPriceParam.getFormat());
        ZhongtongResult<ZhongtongPriceData> zhongtongResult = JSON.parseObject(responseData,
            new TypeReference<ZhongtongResult<ZhongtongPriceData>>() {
            });
        if (!zhongtongResult.getStatus()) {
            return ExpressResponse.failed(zhongtongResult.getMessage());
        }
        ExpressPriceResult expressPriceResult = new ExpressPriceResult();
        expressPriceResult.setPrice(
            new BigDecimal(zhongtongResult.getResult().getPrice()).setScale(2));
        expressPriceResult.setTime(
            new BigDecimal(zhongtongResult.getResult().getHour()).setScale(2));
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

