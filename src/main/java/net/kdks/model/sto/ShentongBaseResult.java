package net.kdks.model.sto;

import lombok.Data;

/**
 * 申通轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ShentongBaseResult {
	/**
	 * 是否成功
	 */
	private Boolean success;
	/**
	 * 错误编码
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	
}
