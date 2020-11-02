package net.kdks.model;

import java.io.Serializable;

/**
 * 收寄人信息.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ContactInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 公司名
	 */
	private String company;
	/**
	 * 联系人姓名
	 */
	private String contact;

	/**
	 * 邮编
	 */
	private String postCode;

	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;

	/**
	 * 区
	 */
	private String county;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 电话
	 */
	private String tel;
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ContactInfo [company=" + company + ", contact=" + contact + ", postCode=" + postCode + ", province="
				+ province + ", city=" + city + ", county=" + county + ", address=" + address + ", tel=" + tel + "]";
	}
	
	

}
