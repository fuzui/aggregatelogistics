package net.kdks.model.sf;

/**
 * 顺丰轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ApiResultData {
	/**
	 * 是否成功
	 */
	private Boolean success;
	/**
	 * 错误码
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMsg;
	/**
	 * 查询信息
	 */
	private MsgData msgData;

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

	public MsgData getMsgData() {
		return msgData;
	}

	public void setMsgData(MsgData msgData) {
		this.msgData = msgData;
	}

	@Override
	public String toString() {
		return "ApiResultData [success=" + success + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", msgData=" + msgData + "]";
	}
	
	
}
