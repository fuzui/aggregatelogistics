package net.kdks.model.zto;

import java.util.List;

/**
 * 中通轨迹查询数据.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ZhongtongData {
	/**
	 * 路由信息
	 */
	private List<ZhongtongTrace> traces;
	/**
	 * 快递号
	 */
	private String billCode;
	public List<ZhongtongTrace> getTraces() {
		return traces;
	}
	public void setTraces(List<ZhongtongTrace> traces) {
		this.traces = traces;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	@Override
	public String toString() {
		return "ZhongtongData [traces=" + traces + ", billCode=" + billCode + "]";
	}
	
	
	
}
