package net.kdks.model;

import java.io.Serializable;

/**
 * 下单结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class OrderResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 通讯状态
	 */
	private Integer status;
	/**
	 * 提示信息
	 */
	private String message;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 运单号
	 */
	private String expressNo;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderResult [status=" + status + ", message=" + message + ", orderId=" + orderId + ", expressNo="
				+ expressNo + "]";
	}

	
	
	
	
}
