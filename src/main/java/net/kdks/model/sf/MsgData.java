package net.kdks.model.sf;

import java.util.List;

public class MsgData {
	private List<RouteResps> routeResps;

	
	public List<RouteResps> getRouteResps() {
		return routeResps;
	}


	public void setRouteResps(List<RouteResps> routeResps) {
		this.routeResps = routeResps;
	}


	@Override
	public String toString() {
		return "MsgData [routeResps=" + routeResps + "]";
	}

	
}
