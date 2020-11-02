package net.kdks.model.sf;

import com.alibaba.fastjson.JSON;

/**
 * 顺丰轨迹查询请求结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShunfengResult {
	private String apiErrorMsg;
	
	private String apiResponseID;
	
	private String apiResultCode;
	
	private ApiResultData apiResultData;

	public String getApiErrorMsg() {
		return apiErrorMsg;
	}

	public void setApiErrorMsg(String apiErrorMsg) {
		this.apiErrorMsg = apiErrorMsg;
	}

	public String getApiResponseID() {
		return apiResponseID;
	}

	public void setApiResponseID(String apiResponseID) {
		this.apiResponseID = apiResponseID;
	}

	public String getApiResultCode() {
		return apiResultCode;
	}

	public void setApiResultCode(String apiResultCode) {
		this.apiResultCode = apiResultCode;
	}

	public ApiResultData getApiResultData() {
		return apiResultData;
	}

	public void setApiResultData(String apiResultData) {
		
		this.apiResultData = JSON.parseObject(apiResultData, ApiResultData.class);
	}

	@Override
	public String toString() {
		return "ShunfengResult [apiErrorMsg=" + apiErrorMsg + ", apiResponseID=" + apiResponseID + ", apiResultCode="
				+ apiResultCode + ", apiResultData=" + apiResultData + "]";
	}
	
	
	
	
}
