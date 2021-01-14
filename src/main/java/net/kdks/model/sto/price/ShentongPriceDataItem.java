package net.kdks.model.sto.price;

/**
 * 申通运单数据.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShentongPriceDataItem {
	
	private ShentongFeeModel feeModel;

	public ShentongFeeModel getFeeModel() {
		return feeModel;
	}

	public void setFeeModel(ShentongFeeModel feeModel) {
		this.feeModel = feeModel;
	}

	@Override
	public String toString() {
		return "ShentongPriceDataItem [feeModel=" + feeModel + "]";
	}
}
