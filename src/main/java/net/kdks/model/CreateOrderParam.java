package net.kdks.model;

import java.io.Serializable;

/**
 * 下单参数.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class CreateOrderParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 物品信息
	 */
	private CargoDetail cargoDetail;
	
	/**
	 * 发件人信息
	 */
	private ContactInfo sendContactInfo;
	
	/**
	 * 收件人信息
	 */
	private ContactInfo receiptContactInfo;

	/**
	 * 手机号
	 */
	private String orderId;

	public CargoDetail getCargoDetail() {
		return cargoDetail;
	}

	public void setCargoDetail(CargoDetail cargoDetail) {
		this.cargoDetail = cargoDetail;
	}

	public ContactInfo getSendContactInfo() {
		return sendContactInfo;
	}

	public void setSendContactInfo(ContactInfo sendContactInfo) {
		this.sendContactInfo = sendContactInfo;
	}

	public ContactInfo getReceiptContactInfo() {
		return receiptContactInfo;
	}

	public void setReceiptContactInfo(ContactInfo receiptContactInfo) {
		this.receiptContactInfo = receiptContactInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CreateOrderParam [cargoDetail=" + cargoDetail + ", sendContactInfo=" + sendContactInfo
				+ ", receiptContactInfo=" + receiptContactInfo + ", orderId=" + orderId + ", getCargoDetail()="
				+ getCargoDetail() + ", getSendContactInfo()=" + getSendContactInfo() + ", getReceiptContactInfo()="
				+ getReceiptContactInfo() + ", getOrderId()=" + getOrderId() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

	

}
