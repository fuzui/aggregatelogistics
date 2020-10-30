package net.kdks.model.zto;

import java.util.List;
import java.util.Map;

import net.kdks.model.sto.ShentongRoute;

/**
 * 申通返回结果
 * @author wangze
 * @date 2020-10-10 16:54:38
 */
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
	
	public List<ZhongtongData> getData() {
		return data;
	}
	public void setData(List<ZhongtongData> data) {
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ZhongtongResult [data=" + data + ", msg=" + msg + ", status=" + status + ", message=" + message + "]";
	}
	
	
	
	
	
	
}
