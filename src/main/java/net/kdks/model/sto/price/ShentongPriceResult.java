package net.kdks.model.sto.price;

import net.kdks.model.sto.ShentongBaseResult;

/**
 * 申通轨迹查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShentongPriceResult extends ShentongBaseResult {
	private ShentongPriceData data;

	public ShentongPriceData getData() {
		return data;
	}

	public void setData(ShentongPriceData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ShentongPriceResult [data=" + data + ", getSuccess()=" + getSuccess() + ", getErrorCode()="
				+ getErrorCode() + ", getErrorMsg()=" + getErrorMsg() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
