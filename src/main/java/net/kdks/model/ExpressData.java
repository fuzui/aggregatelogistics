package net.kdks.model;

import java.io.Serializable;

import lombok.Data;


/**
 * 路由信息.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ExpressData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 物流轨迹节点内容
	 */
	private String context;
	
	/**
	 * 	时间，原始格式
	 */
	private Long time;
	/**
	 * 格式化后时间
	 */
	private String ftime;
	/**
	 * 本数据元对应的签收状态。只有在开通签收状态服务（见上面"status"后的说明）且在接口中提交resultv2标记后才会出现
	 */
	private Integer status;

	/**
	 * 本数据元对应的行政区域的编码，只有在开通签收状态服务（见上面"status"后的说明）且在接口中提交resultv2标记后才会出现
	 */
	private String areaCode;

	/**
	 * 本数据元对应的行政区域的名称，开通签收状态服务（见上面"status"后的说明）且在接口中提交resultv2标记后才会出现
	 */
	private String areaName;

}
