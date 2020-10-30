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
public class CargoDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 快递公司官方简称
	 */
	private String cargoDetail;
	
	/**
	 * 	快递号
	 */
	private String expressNo;

	/**
	 * 手机号
	 */
	private String mobile;

	

}
