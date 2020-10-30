package net.kdks.model;

import java.io.Serializable;

/**
 * 物流查询入参
 * @ApplicationName: tnschool-server-common-core
 * @Title: LogisticsParam.java
 * @Package: cn.topnew.school.common.core.result.logistics
 * @author: wang ze
 * @date: 2020年9月17日 下午6:08:07
 */
public class ExpressParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 快递公司官方简称
	 */
	private String expressCompanyNo;
	
	/**
	 * 	快递号
	 */
	private String expressNo;

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

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
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
