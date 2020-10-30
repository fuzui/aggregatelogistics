package net.kdks.model;

import java.io.Serializable;

/**
 * 下单结果
 * @author wangze
 * @date 2020-10-29 15:38:56
 */
public class OrderResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderResult [orderId=" + orderId + "]";
	}
	
	
	
}
