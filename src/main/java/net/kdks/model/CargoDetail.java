package net.kdks.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 物品信息.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class CargoDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数量
	 */
	private BigDecimal count;

	/**
	 * 单位
	 */
	private String unit;

	/**
	 * 重量
	 */
	private BigDecimal weight;
	
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 币种
	 */
	private String currency;

	/**
	 * 名字
	 */
	private String name;

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CargoDetail [count=" + count + ", unit=" + unit + ", weight=" + weight + ", amount=" + amount
				+ ", currency=" + currency + ", name=" + name + "]";
	}

	
	
	

}
