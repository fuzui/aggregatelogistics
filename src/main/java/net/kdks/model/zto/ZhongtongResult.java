package net.kdks.model.zto;

import java.util.List;

import lombok.Data;

/**
 * 中通轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ZhongtongResult {
	/**
	 * 数据
	 */
	private List<ZhongtongData> data;
	/**
	 * 信息
	 */
	private String msg;
	/**
	 * 是否成功
	 */
	private Boolean status;
	/**
	 * 错误信息
	 */
	private String message;
	
}
