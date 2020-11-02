package net.kdks.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 物流查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 消息体
	 */
	private String message;
	/**
	 * 0在途，1揽收，2疑难，3签收，4退回，5派件，6转投，7转投
	 */
	private Integer state;
	/**
	 * 通讯状态
	 */
	private Integer status;
	
	/**
	 * 	是否签收标记
	 */
	private Integer ischeck;

	/**
	 * 	快递110中快递公司编码
	 */
	private String com;
	/**
	 * 快递单号
	 */
	private String nu;

	private List<ExpressData> data;
	
	private String originalResult;

	public static ExpressResult restResult(String msg) {
		ExpressResult apiResult = new ExpressResult();
		apiResult = JSONObject.parseObject(msg, ExpressResult.class);
		return apiResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIscheck() {
		return ischeck;
	}

	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public List<ExpressData> getData() {
		return data;
	}

	public void setData(List<ExpressData> data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public String getOriginalResult() {
		return originalResult;
	}

	public void setOriginalResult(String originalResult) {
		this.originalResult = originalResult;
	}

	@Override
	public String toString() {
		return "ExpressResult [message=" + message + ", state=" + state + ", status=" + status + ", ischeck=" + ischeck
				+ ", com=" + com + ", nu=" + nu + ", data=" + data + ", originalResult=" + originalResult + "]";
	}

	
	
}
