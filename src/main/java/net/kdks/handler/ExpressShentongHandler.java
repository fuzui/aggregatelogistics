package net.kdks.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import net.kdks.config.ShentongConfig;
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
import net.kdks.model.sto.ShentongResult;
import net.kdks.model.sto.ShentongRoute;
import net.kdks.model.sto.price.ShentongPriceResult;
import net.kdks.request.ShentongRequest;

/**
 * 申通.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressShentongHandler implements ExpressHandler {

	private ShentongRequest shentongRequest;
	
	public ExpressShentongHandler(ShentongConfig shentongConfig) {
		this.shentongRequest = new ShentongRequest(shentongConfig);
	}
	
	/**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
	@Override
	public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam) {
		Map<String, Object> paramsItem = new HashMap<>(2);
		List<String> expressNos = expressParam.getExpressNos();
		paramsItem.put("order", "desc");
		paramsItem.put("waybillNoList", expressNos);
		String param = JSON.toJSONString(paramsItem);
		String responseData = shentongRequest.queryRouteRequest(param, expressParam.getFormat());
		return disposeResult(responseData, expressParam);

	}

	/**
	 * 结果处理
	 * 
	 * @param responseData
	 * @return
	 */
	private ExpressResponse<List<ExpressResult>> disposeResult(String responseData, ExpressParam expressParam) {
		List<String> expressNos = expressParam.getExpressNos();
		ShentongResult result = new ShentongResult();
		List<ExpressResult> expressResults = new ArrayList<ExpressResult>();
		try {
			result = JSON.parseObject(responseData, ShentongResult.class);
		} catch (JSONException e) {
			String messageStart = "<errorMsg>";
			String messageEnd = "</errorMsg>";
			int strStartIndex = responseData.indexOf(messageStart);
	        int strEndIndex = responseData.indexOf(messageEnd);
	        String errorResult = responseData.substring(strStartIndex, strEndIndex).substring(messageStart.length());
			return ExpressResponse.failed(errorResult);
		}
		if (result.getSuccess()) {
			for(String expressNo: expressNos) {
				List<ShentongRoute> routes = result.getData().get(expressNo);
				ExpressResult expressResult = new ExpressResult();
				if(expressParam.isViewOriginal()) {
					expressResult.setOriginalResult(responseData);
				}
				expressResult.setCom(ExpressCompanyCodeEnum.STO.getValue());
				expressResult.setNu(expressNo);
				if(routes == null || routes.size() == 0) {
					expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
					expressResult.setMsg(CommonConstant.NO_INFO);
					expressResults.add(expressResult);
					continue;
				}
				ExpressData latestData = routes.get(0);
   				if(expressParam.isViewRoute()) {
   					List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
   					for (ShentongRoute route : routes) {
   						data.add(route);
   					}
   					expressResult.setData(data);
   				}
				expressResult.setState(latestData.getStatus());
				if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
					expressResult.setIscheck(CommonConstant.YES);
				}
				expressResults.add(expressResult);
			}
			return ExpressResponse.ok(expressResults);
		} 
		return ExpressResponse.failed(result.getErrorMsg());
	}
	
	/**
	 * 运费预估
	 * 
	 * @param expressPriceParam 起始省份、起始城市、目的身份、目的城市、重量、长、宽、高
	 * @return 运费
	 */
	@Override
	public ExpressResponse<ExpressPriceResult> getExpressPrice(ExpressPriceParam expressPriceParam) {
		Map<String, Object> paramsItem = new HashMap<>(2);
		paramsItem.put("SendProv", expressPriceParam.getStartProvince());
		paramsItem.put("SendCity", expressPriceParam.getStartCity());
		paramsItem.put("SendArea", expressPriceParam.getStartDistrict());
		paramsItem.put("SendAddress", expressPriceParam.getStartAddress());
		
		paramsItem.put("RecProv", expressPriceParam.getEndProvince());
		paramsItem.put("RecCity", expressPriceParam.getEndCity());
		paramsItem.put("RecArea", expressPriceParam.getEndDistrict());
		paramsItem.put("RecAddress", expressPriceParam.getEndAddress());
		
		paramsItem.put("Weight", expressPriceParam.getWeight());
		paramsItem.put("Length", expressPriceParam.getLength());
		paramsItem.put("Width", expressPriceParam.getWidth());
		paramsItem.put("Height", expressPriceParam.getHeight());
		String param = JSON.toJSONString(paramsItem);
		String responseData = shentongRequest.queryPriceRequest(param, expressPriceParam.getFormat());
		System.out.println(responseData);
		ShentongPriceResult shentongPriceResult = new ShentongPriceResult();
		ExpressPriceResult result = new ExpressPriceResult();
		try {
			shentongPriceResult = JSON.parseObject(responseData, ShentongPriceResult.class);
		} catch (JSONException e) {
			String messageStart = "<errorMsg>";
			String messageEnd = "</errorMsg>";
			int strStartIndex = responseData.indexOf(messageStart);
	        int strEndIndex = responseData.indexOf(messageEnd);
	        String errorResult = responseData.substring(strStartIndex, strEndIndex).substring(messageStart.length());
			return ExpressResponse.failed(errorResult);
		}
		if (shentongPriceResult.getSuccess()) {
			System.out.println(shentongPriceResult.getData());
			BigDecimal price = new BigDecimal(shentongPriceResult.getData().getAvailableServiceItemList().get(0).getFeeModel().getTotalPrice()).movePointLeft(2).setScale(2);
			BigDecimal time = new BigDecimal(shentongPriceResult.getData().getAgeing()).setScale(2);
			result.setPrice(price);
			result.setTime(time);
			return  ExpressResponse.ok(result);
		}
		return ExpressResponse.failed(shentongPriceResult.getErrorMsg());
	}
	
	/**
     * 创建订单
     * @param createOrderParam	下单参数，主要包含物品信息、收件人信息、寄件人信息等
     * @return	快递单号等信息
     */
	@Override
	public ExpressResponse<OrderResult> createOrder(CreateOrderParam createOrderParam) {
		return ExpressResponse.failed(CommonConstant.NO_SOPPORT);
	}

	/**
     * 获取当前快递公司编码
     * @return 快递公司编码
     */
	@Override
	public String getExpressCompanyCode() {
		return ExpressCompanyCodeEnum.STO.getValue();
	}

}
