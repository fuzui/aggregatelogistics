package net.kdks.model.sf;

public class ApiResultData {
	private Boolean success;
	
	private String errorCode;
	
	private String errorMsg;
	
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
