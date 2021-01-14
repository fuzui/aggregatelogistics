package net.kdks.model.sf;

import com.alibaba.fastjson.JSON;

import lombok.Data;

/**
 * 顺丰轨迹查询请求结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ShunfengResult {
	private String apiErrorMsg;
	
	private String apiResponseID;
	
	private String apiResultCode;
	
	private ApiResultData apiResultData;

	public void setApiResultData(String apiResultData) {
		
		this.apiResultData = JSON.parseObject(apiResultData, ApiResultData.class);
	}
}
