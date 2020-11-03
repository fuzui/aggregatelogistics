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
	 * 订单号
	 */
	private String orderId;
	/**
	 * 运单号
	 */
	private String expressNo;


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
		return "OrderResult [orderId=" + orderId + ", expressNo="
				+ expressNo + "]";
	}

	
	
	
	
}
