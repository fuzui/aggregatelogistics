package net.kdks.model.zto.price;

import java.util.List;

import net.kdks.model.zto.ZhongtongData;

/**
 * 中通物流预估结果.
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
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
	
	public ZhongtongPriceData getData() {
		return data;
	}
	public void setData(ZhongtongPriceData data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ZhongtongPriceResult [data=" + data + ", msg=" + msg + ", status=" + status + "]";
	}
	
}
