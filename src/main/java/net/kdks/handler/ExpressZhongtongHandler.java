package net.kdks.handler;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.hutool.http.HttpRequest;
import net.kdks.config.ZhongtongConfig;
import net.kdks.constant.CommonConstant;
import net.kdks.constant.HttpStatusCode;
import net.kdks.enums.ExpressCompanyCodeEnum;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.model.ExpressParam;
import net.kdks.model.ExpressResult;
import net.kdks.model.OrderResult;
import net.kdks.model.zto.ZhongtongData;
import net.kdks.model.zto.ZhongtongResult;
import net.kdks.model.zto.ZhongtongTrace;
import net.kdks.utils.DigestUtils;
import net.kdks.utils.StringUtils;

/**
 * 中通
 * @author wangze
 * @date 2020-10-11 22:14:10
 */
public class ExpressZhongtongHandler implements ExpressHandler {

	private ZhongtongConfig zhongtongConfig;
	
	public ExpressZhongtongHandler(ZhongtongConfig zhongtongConfig) {
		this.zhongtongConfig = zhongtongConfig;
	}
	
    @Override
    public ExpressResult getExpressInfo(ExpressParam expressParam) {
        String requestUrl = "http://japi.zto.cn/traceInterfaceNewTraces";
        String companyId = zhongtongConfig.getCompanyId();
        String secretKey = zhongtongConfig.getSecretKey();
        if(zhongtongConfig.getIsProduct() == 0) {
        	requestUrl = "https://japi-test.zto.com/traceInterfaceNewTraces";
            companyId = "kfpttestCode";
            secretKey = "kfpttestkey==";
		}
        
        HashMap<String, Object> paramMap = new HashMap<>();
		String[] expressNo = {expressParam.getExpressNo()};
		paramMap.put("company_id", companyId);
		paramMap.put("msg_type", "NEW_TRACES");
		paramMap.put("data", JSON.toJSONString(expressNo));
		String beforeDigestStr = StringUtils.buildMapToStr(paramMap, "UTF-8")+secretKey;
		String dataDigest = Base64.getEncoder().encodeToString(DigestUtils.Md5(beforeDigestStr));
		Map<String,String> requestHeader = new HashMap<String,String>();
		requestHeader.put("x-companyid", companyId);
		requestHeader.put("x-datadigest", dataDigest);
		requestHeader.put("ContentType", "application/x-www-form-urlencoded; charset=utf-8");
		String responseData = HttpRequest.post(requestUrl)
			    .addHeaders(requestHeader)
			    .body(StringUtils.buildMapToStrUrl(paramMap, "UTF-8"))
			    .execute().body();
		System.out.println(responseData);
        return disposeResult(responseData, expressNo[0]);
        
    }
    
    /**
   	 * 结果处理
   	 * 
   	 * @param responseData
   	 * @return
   	 */
   	private ExpressResult disposeResult(String responseData, String expressNo) {
   		ZhongtongResult result = JSON.parseObject(responseData, ZhongtongResult.class);
   		ExpressResult expressResult = new ExpressResult();
   		expressResult.setOriginalResult(responseData);
   		expressResult.setCom(ExpressCompanyCodeEnum.ZTO.getValue());
   		expressResult.setNu(expressNo);
   		if (result.getStatus()) {
   			
   			expressResult.setStatus(HttpStatusCode.SUCCESS);
   			List<ZhongtongData> zhongtongData = result.getData();
   			if(zhongtongData == null || zhongtongData.size() == 0) {
   				expressResult.setStatus(HttpStatusCode.EXCEPTION);
   	   			expressResult.setMessage(CommonConstant.NO_INFO);
   	   			return expressResult;
   			}
   			List<ZhongtongTrace> routes = zhongtongData.get(0).getTraces();
   			if(routes != null && routes.size() != 0) {
   				//官方默认正序，改为倒序
   				Collections.reverse(routes);
   				List<ExpressData> data = new ArrayList<ExpressData>(routes.size());
   				for (ZhongtongTrace route : routes) {
   					data.add(route);
   				}
   				expressResult.setState(data.get(0).getStatus());
   				if (expressResult.getState() == ExpressStateEnum.SIGNED.getValue()) {
   					expressResult.setIscheck(CommonConstant.YES);
   				}
   				expressResult.setData(data);
   			}else {
   				expressResult.setStatus(HttpStatusCode.EXCEPTION);
   	   			expressResult.setMessage(CommonConstant.NO_INFO);
   	   			return expressResult;
   			}
   		} else {
   			expressResult.setStatus(HttpStatusCode.EXCEPTION);
   			expressResult.setMessage(result.getMessage());
   		}
   		return expressResult;
   	}
   	
   	@Override
	public OrderResult createOrder() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public String getExpressCompanyCode() {
        return ExpressCompanyCodeEnum.ZTO.getValue();
    }
    
}

