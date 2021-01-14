package net.kdks.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运费估算结果.
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
public class ExpressPriceResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 时效
	 */
	private BigDecimal time;
	/**
	 * 邮费
	 */
	private BigDecimal price;

	public BigDecimal getTime() {
		return time;
	}

	public void setTime(BigDecimal time) {
		this.time = time;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ExpressPriceResult [time=" + time + ", price=" + price + "]";
	}

}
