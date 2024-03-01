package net.kdks.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import net.kdks.config.JingdongConfig;
import net.kdks.config.JituConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.constant.JingdongConstant;
import net.kdks.constant.JituConstant;
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
import net.kdks.model.jd.JingdongResult;
import net.kdks.model.jd.route.JingdongRouteItem;
import net.kdks.model.jd.route.JingdongRouteResult;
import net.kdks.model.jt.JituResult;
import net.kdks.model.jt.price.JituPriceResult;
import net.kdks.model.jt.route.JituRouteItem;
import net.kdks.model.jt.route.JituRouteResult;
import net.kdks.request.JingdongRequest;
import net.kdks.request.JituRequest;
import net.kdks.utils.Assert;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.MapUtils;

/**
 * 京东.
 *
 * @author Ze.Wang
 * @since 0.0.11
 */
public class ExpressJingdongHandler implements ExpressHandler {


    private JingdongRequest jingdongRequest;

    private JingdongConfig jingdongConfig;

    public ExpressJingdongHandler(JingdongConfig jingdongConfig) {
        this.jingdongConfig = jingdongConfig;
        this.jingdongRequest = new JingdongRequest(jingdongConfig);
    }

    /**
     * 查询轨迹信息.
     *
     * @param expressParam 快递号、手机、快递公司编码
     * @return 查询接口
     */
    @Override
    public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam) {
        List<ExpressResult> expressResults = new ArrayList<>();
        for (String expressNo : expressParam.getExpressNos()) {
            Map<String, String> paramMap = MapUtils.newHashMap(3);
            paramMap.put("waybillCode", expressNo);
            paramMap.put("orderOrigin", "1");
            paramMap.put("customerCode", jingdongConfig.getCustomerCode());
            List<Map<String, String>> paramList = new ArrayList<>();
            paramList.add(paramMap);
            String param = JSON.toJSONString(paramList);
            String responseData = jingdongRequest.queryRouteRequest(param, expressParam.getFormat());
            ExpressResult expressResult = disposeResult(responseData, expressNo, expressParam);
            expressResults.add(expressResult);
        }
        return ExpressResponse.ok(expressResults);
    }

    /**
     * 结果处理.
     *
     * @param responseData 响应
     * @return 查询结果
     */
    private ExpressResult disposeResult(String responseData, String expressNo,
                                                               ExpressParam expressParam) {

        ExpressResult expressResult = new ExpressResult();
        if (expressParam.isViewOriginal()) {
            expressResult.setOriginalResult(responseData);
        }
        expressResult.setCom(ExpressCompanyCodeEnum.JD.getValue());
        expressResult.setNu(expressNo);
        JingdongResult<JingdongRouteResult> result =
            JSON.parseObject(responseData,
                new TypeReference<JingdongResult<JingdongRouteResult>>() {});
        if (!JingdongConstant.REQUEST_SUCCESS_CODE.equals(result.getCode())
            || result.getData() == null) {
            expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
            expressResult.setMsg(result.getMsg());
            return expressResult;
        }
        List<JingdongRouteItem> routes = result.getData().getTraceDetails();
        if (routes == null || routes.isEmpty()) {
            expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
            expressResult.setMsg(CommonConstant.NO_INFO);
            return expressResult;
        }
        if (expressParam.isViewRoute()) {
            List<ExpressData> data = new ArrayList<>(routes.size());
            data.addAll(routes);
            expressResult.setData(data);
        }
        expressResult.setState(routes.get(0).getStatus());
        if (ExpressStateEnum.SIGNED.getValue().equals(expressResult.getState())) {
            expressResult.setIscheck(CommonConstant.YES);
        }
        return expressResult;


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
        return ExpressResponse.failed(CommonConstant.NO_SOPPORT);
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
        return ExpressCompanyCodeEnum.JD.getValue();
    }

}

