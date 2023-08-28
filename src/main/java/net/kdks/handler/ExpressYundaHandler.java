package net.kdks.handler;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kdks.config.ShunfengConfig;
import net.kdks.config.YundaConfig;
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
import net.kdks.model.ExpressRouteSubscribeData;
import net.kdks.model.OrderResult;
import net.kdks.model.sf.Route;
import net.kdks.model.sf.RouteResps;
import net.kdks.model.sf.ShunfengResult;
import net.kdks.model.sf.WaybillNoInfo;
import net.kdks.model.yd.YundaResult;
import net.kdks.model.yd.route.YundaRouteItem;
import net.kdks.model.yd.route.YundaRouteResult;
import net.kdks.model.yd.route.YundaRouteSubscribeItem;
import net.kdks.model.yd.route.YundaRouteSubscribeResult;
import net.kdks.model.zto.ZhongtongResult;
import net.kdks.model.zto.ZhongtongTrace;
import net.kdks.request.YundaRequest;
import net.kdks.request.ZhongtongRequest;
import net.kdks.utils.DateUtils;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.MapUtils;
import net.kdks.utils.StringUtils;

/**
 * 顺丰.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressYundaHandler implements ExpressHandler {

    private YundaRequest yundaRequest;
    private YundaConfig yundaConfig;

    public ExpressYundaHandler(YundaConfig yundaConfig) {
        this.yundaConfig = yundaConfig;
        this.yundaRequest = new YundaRequest(yundaConfig);
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
        subscribeRoute(expressParam);
        for (String expressNo : expressParam.getExpressNos()) {
            Map<String, Object> param = MapUtils.newHashMap(1);
            param.put("mailno", expressNo);
            String responseData = yundaRequest.queryRouteRequest(JSON.toJSONString(param),
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
        expressResult.setCom(ExpressCompanyCodeEnum.YD.getValue());
        expressResult.setNu(expressNo);
        List<String> expressNos = expressParam.getExpressNos();
        YundaResult<YundaRouteResult> result = JSON.parseObject(responseData,
            new TypeReference<YundaResult<YundaRouteResult>>() {
            });
        if (!result.getResult()) {
            expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
            expressResult.setMsg(result.getMessage());
            return expressResult;
        }
        if (result.getData() == null || result.getData().getSteps() == null
            || result.getData().getSteps().size() == 0) {
            expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
            expressResult.setMsg(CommonConstant.NO_INFO);
            return expressResult;
        }
        List<YundaRouteItem> routes = result.getData().getSteps();
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
     * 订阅物流轨迹.
     *
     * @param expressParam 快递号、手机、快递公司编码
     * @return 查询接口
     */
    private ExpressResponse<List<ExpressRouteSubscribeData>> subscribeRoute(
        ExpressParam expressParam) {
        List<ExpressResult> expressResults = new ArrayList<>();
        Map<String, Object> param = MapUtils.newHashMap(1);
        List<Map<String, Object>> orders = new ArrayList<>();
        for (String expressNo : expressParam.getExpressNos()) {
            Map<String, Object> order = MapUtils.newHashMap(2);
            order.put("orderid", expressNo);
            order.put("mailno", expressNo);
            orders.add(order);
        }
        param.put("orders", orders);
        String responseData = yundaRequest.subscribeRouteRequest(JSON.toJSONString(param),
            expressParam.getFormat());
        YundaResult<List<YundaRouteSubscribeItem>> result = JSON.parseObject(responseData,
            new TypeReference<YundaResult<List<YundaRouteSubscribeItem>>>() {
            });
        List<YundaRouteSubscribeItem> yundaRouteSubscribeItems = result.getData();
        List<ExpressRouteSubscribeData> data = new ArrayList<>();
        data.addAll(yundaRouteSubscribeItems);
        return ExpressResponse.ok(data);
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
        return ExpressCompanyCodeEnum.YD.getValue();
    }

}

