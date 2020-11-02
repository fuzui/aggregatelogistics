package net.kdks.model.sf;

import java.util.List;

/**
 * 查询信心.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class MsgData {
	/**
	 * 路由集
	 */
	private List<RouteResps> routeResps;

	/**
	 * 单号集
	 */
	private List<WaybillNoInfo> waybillNoInfoList;
	
	public List<RouteResps> getRouteResps() {
		return routeResps;
	}


	public void setRouteResps(List<RouteResps> routeResps) {
		this.routeResps = routeResps;
	}

	public List<WaybillNoInfo> getWaybillNoInfoList() {
		return waybillNoInfoList;
	}

	public void setWaybillNoInfoList(List<WaybillNoInfo> waybillNoInfoList) {
		this.waybillNoInfoList = waybillNoInfoList;
	}

	@Override
	public String toString() {
		return "MsgData [routeResps=" + routeResps + ", waybillNoInfoList=" + waybillNoInfoList + "]";
	}

	
}
