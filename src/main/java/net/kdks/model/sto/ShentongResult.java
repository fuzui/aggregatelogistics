package net.kdks.model.sto;

import java.util.List;
import java.util.Map;

/**
 * 申通轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShentongResult {
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
	/**
	 * 是否重试
	 */
	private Boolean needRetry;
	/**
	 * 请求id
	 */
	private String requestId;
	/**
	 * 异常信息
	 */
	private String expInfo;
	/**
	 * 查询结果Map,key是查询的单号，value是对应的轨迹集合
	 */
	private Map<String,List<ShentongRoute>> data;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Boolean getNeedRetry() {
		return needRetry;
	}
	public void setNeedRetry(Boolean needRetry) {
		this.needRetry = needRetry;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getExpInfo() {
		return expInfo;
	}
	public void setExpInfo(String expInfo) {
		this.expInfo = expInfo;
	}
	public Map<String, List<ShentongRoute>> getData() {
		return data;
	}
	public void setData(Map<String, List<ShentongRoute>> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ShentongResult [success=" + success + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", needRetry=" + needRetry + ", requestId=" + requestId + ", expInfo=" + expInfo + ", data=" + data
				+ "]";
	}
	
	
}
