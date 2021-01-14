package net.kdks.model.zto.price;

/**
 * 中通运费估算数据.
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
public class ZhongtongPriceData {
	/**
	 * 续重
	 */
	private String addMoney;
	/**
	 * 首重
	 */
	private String firstMoney;
	/**
	 * 时效
	 */
	private String hour;
	public String getAddMoney() {
		return addMoney;
	}
	public void setAddMoney(String addMoney) {
		this.addMoney = addMoney;
	}
	public String getFirstMoney() {
		return firstMoney;
	}
	public void setFirstMoney(String firstMoney) {
		this.firstMoney = firstMoney;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	@Override
	public String toString() {
		return "ZhongtongPriceData [addMoney=" + addMoney + ", firstMoney=" + firstMoney + ", hour=" + hour + "]";
	}
	
}
