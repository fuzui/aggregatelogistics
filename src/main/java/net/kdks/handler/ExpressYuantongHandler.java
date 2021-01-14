package net.kdks.handler;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;

import net.kdks.config.YuantongConfig;
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
import net.kdks.model.yto.YuanTongErrorResult;
import net.kdks.model.yto.YuanTongResult;
import net.kdks.request.YuantongRequest;

/**
 * 圆通.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressYuantongHandler implements ExpressHandler {

	private YuantongRequest yuantongRequest;
	
	public ExpressYuantongHandler(YuantongConfig yuantongConfig) {
		this.yuantongRequest = new YuantongRequest(yuantongConfig);
	}
	
	/**
     * 查询轨迹信息
     * 
     * @param expressParam	快递号、手机、快递公司编码
     * @return 查询接口
     */
    @Override
    public ExpressResponse<List<ExpressResult>> getExpressInfo(ExpressParam expressParam) {
    	
		List<ExpressResult> expressResults = new ArrayList<ExpressResult>();
		for(String expressNo: expressParam.getExpressNos()) {
			Map<String, Object>[] paramItemsMap = new Map[1];
	        paramItemsMap[0] = new HashMap<>(1);
			paramItemsMap[0].put("Number", expressNo);
			String param = null;
			try {
				System.out.println(JSON.toJSONString(paramItemsMap));
				param = URLEncoder.encode(JSON.toJSONString(paramItemsMap), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String responseData = yuantongRequest.queryRouteRequest(param, expressParam.getFormat());
			System.out.println(responseData);
			ExpressResult expressResult = disposeResult(responseData, expressNo, expressParam);
			expressResults.add(expressResult);
		}
        return ExpressResponse.ok(expressResults);
    }
    /**
	 * 结果处理
	 * 
	 * @param responseData
	 * @return
	 */
	private ExpressResult disposeResult(String responseData, String expressNo, ExpressParam expressParam) {
		List<ExpressResult> expressResults = new ArrayList<ExpressResult>();
		
		ExpressResult expressResult = new ExpressResult();
		if(expressParam.isViewOriginal()) {
			expressResult.setOriginalResult(responseData);
		}
		expressResult.setCom(ExpressCompanyCodeEnum.YTO.getValue());
		expressResult.setNu(expressNo);
		
		try {
			List<YuanTongResult> routes = new ArrayList<YuanTongResult>();
			routes = JSON.parseObject(responseData, new TypeReference<ArrayList<YuanTongResult>>(){});
			if(routes == null || routes.size() == 0) {
				expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
				expressResult.setMsg(CommonConstant.NO_INFO);
				return expressResult;
			}
			//官方默认正序，改为倒序
			Collections.reverse(routes);
			ExpressData latestData = routes.get(0);
			if(expressParam.isViewRoute()) {
				List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
				for (YuanTongResult route : routes) {
					data.add(route);
				}
				expressResult.setData(data);
			}
			
			expressResult.setState(latestData.getStatus());
			if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
				expressResult.setIscheck(CommonConstant.YES);
			}
			expressResults.add(expressResult);
			return expressResult;
		} catch (JSONException e) {
			YuanTongErrorResult errorResult = new YuanTongErrorResult();
			expressResult.setState(ExpressStateEnum.NO_INFO.getValue());
			try {
				errorResult = JSON.parseObject(responseData, YuanTongErrorResult.class);
			} catch (JSONException e2) {
				String messageStart = "<reason>";
				String messageEnd = "</reason>";
				int strStartIndex = responseData.indexOf(messageStart);
		        int strEndIndex = responseData.indexOf(messageEnd);
		        String result = responseData.substring(strStartIndex, strEndIndex).substring(messageStart.length());
				errorResult.setMessage(result);
			}
			expressResult.setMsg(errorResult.getMessage());
			return expressResult;
		}
	}
	
	/**
	 * 运费预估
	 * 
	 * @param expressPriceParam 起始省份、起始城市、目的身份、目的城市、重量、长、宽、高
	 * @return 运费
	 */
	@Override
	public ExpressResponse<ExpressPriceResult> getExpressPrice(ExpressPriceParam expressPriceParam) {
		Map<String, Object>[] paramItemsMap = new Map[1];
        paramItemsMap[0] = new HashMap<>(1);
		paramItemsMap[0].put("StartProvince", expressPriceParam.getStartProvince());
		paramItemsMap[0].put("StartCity", expressPriceParam.getStartCity());
		paramItemsMap[0].put("EndProvince", expressPriceParam.getEndProvince());
		paramItemsMap[0].put("EndCity", expressPriceParam.getEndCity());
		paramItemsMap[0].put("GoodsWeight", expressPriceParam.getWeight());
		paramItemsMap[0].put("GoodsLength", expressPriceParam.getLength());
		paramItemsMap[0].put("GoodsWidth", expressPriceParam.getWidth());
		paramItemsMap[0].put("GoodsHeight", expressPriceParam.getHeight());
		String param = null;
		try {
			System.out.println(JSON.toJSONString(paramItemsMap));
			param = URLEncoder.encode(JSON.toJSONString(paramItemsMap), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(param.toString());
		String responseData = yuantongRequest.queryPriceRequest(param, expressPriceParam.getFormat());
		System.out.println(responseData);
		try {
			BigDecimal price = new BigDecimal(responseData).setScale(2);
			ExpressPriceResult expressPriceResult = new ExpressPriceResult();
			expressPriceResult.setPrice(price);
			return ExpressResponse.ok(expressPriceResult);
		} catch (NumberFormatException e) {
			String messageStart = "<reason>";
			String messageEnd = "</reason>";
			int strStartIndex = responseData.indexOf(messageStart);
	        int strEndIndex = responseData.indexOf(messageEnd);
	        String result = responseData.substring(strStartIndex, strEndIndex).substring(messageStart.length());
	        return ExpressResponse.failed(result);
		}
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
    	return ExpressCompanyCodeEnum.YTO.getValue();
    }

}

