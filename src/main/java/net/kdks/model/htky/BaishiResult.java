package net.kdks.model.htky;

import java.util.List;

import lombok.Data;

/**
 * 百世轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class BaishiResult {
	/**
	 * 结果描述，true成功，false失败
	 */
	private Boolean result;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 	错误描述
	 */
	private String errorDescription;
	
	private List<BaishiTraceLog> traceLogs;
	
}
