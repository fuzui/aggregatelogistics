package net.kdks.model;

import java.io.Serializable;

import net.kdks.constant.RequestConstant;

/**
 * 物流查询入参.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class BaseParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String format = RequestConstant.JSON;
	
	/**
	 * 快递公司官方简称
	 */
	private String expressCompanyNo;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getExpressCompanyNo() {
		return expressCompanyNo;
	}

	public void setExpressCompanyNo(String expressCompanyNo) {
		this.expressCompanyNo = expressCompanyNo;
	}
	
	
}