package net.kdks.model.sto;

/**
 * 申通轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
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
	@Override
	public String toString() {
		return "ShentongBaseResult [success=" + success + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
	
}
