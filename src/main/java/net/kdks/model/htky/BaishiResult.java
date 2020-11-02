package net.kdks.model.htky;

import java.util.List;

/**
 * 百世轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
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

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public List<BaishiTraceLog> getTraceLogs() {
		return traceLogs;
	}

	public void setTraceLogs(List<BaishiTraceLog> traceLogs) {
		this.traceLogs = traceLogs;
	}

	@Override
	public String toString() {
		return "BaishiResult [result=" + result + ", remark=" + remark + ", errorCode=" + errorCode
				+ ", errorDescription=" + errorDescription + ", traceLogs=" + traceLogs + "]";
	}
	
	
}
