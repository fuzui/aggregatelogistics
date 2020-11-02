package net.kdks.model.sf;

/**
 * 单号.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class WaybillNoInfo {
	/**
	 * 运单号类型1：母单 2 :子单 3 : 签回单
	 */
	private Integer waybillType;
	
	/**
	 * 运单号
	 */
	private String waybillNo;

	public Integer getWaybillType() {
		return waybillType;
	}

	public void setWaybillType(Integer waybillType) {
		this.waybillType = waybillType;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	@Override
	public String toString() {
		return "WaybillNoInfo [waybillType=" + waybillType + ", waybillNo=" + waybillNo + "]";
	}
	
	
}
