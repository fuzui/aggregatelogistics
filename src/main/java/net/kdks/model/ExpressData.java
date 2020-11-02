package net.kdks.model;


import java.io.Serializable;


/**
 * 路由信息.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ExpressData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 物流轨迹节点内容
	 */
	 String context;
	
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

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ExpressData [context=" + context + ", time=" + time + ", ftime=" + ftime + ", status=" + status
				+ ", areaCode=" + areaCode + ", areaName=" + areaName + "]";
	}

	
	

}
