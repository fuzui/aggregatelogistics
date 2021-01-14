package net.kdks.model.sf;

import java.util.List;

import lombok.Data;

/**
 * 路由集.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class RouteResps {
	/**
	 * 单号
	 */
	private String mailNo;
	/**
	 * 路由集合
	 */
	private List<Route> routes;
	
}
