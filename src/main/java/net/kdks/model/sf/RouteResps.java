package net.kdks.model.sf;

import java.util.List;

/**
 * 路由集.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class RouteResps {
	/**
	 * 单号
	 */
	private String mailNo;
	/**
	 * 路由集合
	 */
	private List<Route> routes;

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "RouteResps [mailNo=" + mailNo + ", routes=" + routes + "]";
	}
	
	
}
