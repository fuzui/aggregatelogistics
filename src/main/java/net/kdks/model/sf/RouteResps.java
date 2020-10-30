package net.kdks.model.sf;

import java.util.List;

public class RouteResps {
	private String mailNo;
	
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
