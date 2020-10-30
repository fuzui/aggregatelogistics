package net.kdks.model.zto;

import java.util.List;
import java.util.Map;

import net.kdks.model.sto.ShentongRoute;

/**
 * 申通返回结果
 * @author wangze
 * @date 2020-10-10 16:54:38
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
