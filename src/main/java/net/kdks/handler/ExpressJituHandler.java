package net.kdks.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import net.kdks.config.JituConfig;
import net.kdks.constant.CommonConstant;
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
import net.kdks.model.jt.JituResult;
import net.kdks.model.jt.price.JituPriceResult;
import net.kdks.model.jt.route.JituRouteItem;
import net.kdks.model.jt.route.JituRouteResult;
import net.kdks.request.JituRequest;
import net.kdks.utils.Assert;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.MapUtils;

/**
 * 极兔.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
public class ExpressJituHandler implements ExpressHandler {


    private JituRequest jituRequest;

    private JituConfig jituConfig;

    public ExpressJituHandler(JituConfig jituConfig) {
        this.jituConfig = jituConfig;
        this.jituRequest = new JituRequest(jituConfig);
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
        String billCodes = String.join(",", expressNos);
        Map<String, String> paramMap = MapUtils.newHashMap(1);
        paramMap.put("billCodes", billCodes);
        String param = JSON.toJSONString(paramMap);
        String responseData = jituRequest.queryRouteRequest(param, expressParam.getFormat());
        return disposeResult(responseData, expressParam);
    }

    /**
     * 结果处理.
     *
     * @param responseData 响应
     * @return 查询结果
     */
    private ExpressResponse<List<ExpressResult>> disposeResult(String responseData,
                                                               ExpressParam expressParam) {
        List<String> expressNos = expressParam.getExpressNos();
        JituResult<List<JituRouteResult>> result =
            JSON.parseObject(responseData, new TypeReference<JituResult<List<JituRouteResult>>>() {
            });
        List<ExpressResult> expressResults = new ArrayList<>();
        if (JituConstant.REQUEST_SUCCESS_CODE.equals(result.getCode())) {
            //此结果必然存在不会出现空指针，无需判断
            if (result.getData() == null || result.getData().size() == 0) {
                return ExpressResponse.failed(CommonConstant.NO_INFO);
            }

            Map<String, JituRouteResult> routeResultMap =
                MapUtils.newHashMap(result.getData().size());
            for (JituRouteResult routeResult : result.getData()) {
                routeResultMap.put(routeResult.getBillCode(), routeResult);
            }
            for (String expressNo : expressNos) {
                ExpressResult expressResult = new ExpressResult();
                if (expressParam.isViewOriginal()) {
                    expressResult.setOriginalResult(responseData);
                }
                expressResult.setCom(ExpressCompanyCodeEnum.JT.getValue());
                expressResult.setNu(expressNo);
                List<JituRouteItem> routes = routeResultMap.get(expressNo).getDetails();
                if (routes == null || routes.size() == 0) {
                    expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
                    expressResult.setMsg(CommonConstant.NO_INFO);
                    expressResults.add(expressResult);
                    continue;
                }
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
        return ExpressResponse.failed(result.getMsg());

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
        checkCustomerCodeAndPwd();
        // 寄件人
        Map<String, Object> senderMap = MapUtils.newHashMap(4);
        senderMap.put("prov", expressPriceParam.getStartProvince());
        senderMap.put("city", expressPriceParam.getStartCity());
        senderMap.put("area", expressPriceParam.getStartDistrict());
        senderMap.put("address", expressPriceParam.getStartAddress());
        // 收件人
        Map<String, Object> receiverMap = MapUtils.newHashMap(4);
        receiverMap.put("prov", expressPriceParam.getEndProvince());
        receiverMap.put("city", expressPriceParam.getEndCity());
        receiverMap.put("area", expressPriceParam.getEndDistrict());
        receiverMap.put("address", expressPriceParam.getEndAddress());

        Map<String, Object> param = MapUtils.newHashMap(4);
        param.put("customerCode", jituConfig.getCustomerCode());

        String pwd =
            DigestUtils.md5DigestToStr(jituConfig.getCustomerPwd() + "jadada236t2").toUpperCase();
        String digest = Base64.getEncoder().encodeToString(
            DigestUtils.md5Digest(jituConfig.getCustomerCode() + pwd + jituConfig.getPrivateKey()));
        param.put("digest", digest);
        param.put("sender", senderMap);
        param.put("receiver", receiverMap);
        param.put("length", expressPriceParam.getLength());
        param.put("width", expressPriceParam.getWidth());
        param.put("height", expressPriceParam.getHeight());
        param.put("weight", expressPriceParam.getWeight());
        String responseData =
            jituRequest.queryPriceRequest(JSON.toJSONString(param),
                expressPriceParam.getFormat());

        JituResult<JituPriceResult> result =
            JSON.parseObject(responseData, new TypeReference<JituResult<JituPriceResult>>() {
            });
        if (JituConstant.REQUEST_SUCCESS_CODE.equals(result.getCode())) {
            return ExpressResponse.ok(result.getData());
        }
        return ExpressResponse.failed(result.getMsg());
    }

    /**
     * 检查客户编号和密码.
     */
    private void checkCustomerCodeAndPwd() {
        Assert.notEmpty(jituConfig.getCustomerCode(), "请配置客户编号");
        Assert.notEmpty(jituConfig.getCustomerPwd(), "请配置客户密码");
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
        return ExpressCompanyCodeEnum.JT.getValue();
    }

}

