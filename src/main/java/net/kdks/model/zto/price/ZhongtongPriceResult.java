package net.kdks.model.zto.price;

import lombok.Data;

/**
 * 中通物流预估结果.
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
@Data
public class ZhongtongPriceResult {
	/**
	 * 数据
	 */
	private ZhongtongPriceData data;
	/**
	 * 信息
	 */
	private String msg;
	/**
	 * 是否成功
	 */
	private Boolean status;
	
}
