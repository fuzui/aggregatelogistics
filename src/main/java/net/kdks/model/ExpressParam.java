package net.kdks.model;

import java.io.Serializable;
import java.util.List;

/**
 * 物流查询入参.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressParam extends BaseParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 	快递号
	 */
	private List<String> expressNos;

	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 是否展示路由
	 */
	private boolean isViewRoute = true;
	
	/**
	 * 是否展示原生信息
	 */
	private boolean isViewOriginal = false;

	public List<String> getExpressNos() {
		return expressNos;
	}

	public void setExpressNos(List<String> expressNos) {
		this.expressNos = expressNos;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public boolean isViewRoute() {
		return isViewRoute;
	}

	public void setViewRoute(boolean isViewRoute) {
		this.isViewRoute = isViewRoute;
	}

	public boolean isViewOriginal() {
		return isViewOriginal;
	}

	public void setViewOriginal(boolean isViewOriginal) {
		this.isViewOriginal = isViewOriginal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}