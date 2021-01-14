package net.kdks.model;

import java.io.Serializable;

/**
 * 物流查询入参.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressBatchParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 快递公司官方简称
	 */
	private String expressCompanyNo;
	
	/**
	 * 	快递号
	 */
	private String[] expressNos;

	/**
	 * 手机号
	 */
	private String mobile;

	public String getExpressCompanyNo() {
		return expressCompanyNo;
	}

	public void setExpressCompanyNo(String expressCompanyNo) {
		this.expressCompanyNo = expressCompanyNo;
	}

	
	public String[] getExpressNos() {
		return expressNos;
	}

	public void setExpressNos(String[] expressNos) {
		this.expressNos = expressNos;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
