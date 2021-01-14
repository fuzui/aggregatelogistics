package net.kdks.model.sto.price;
/**
 * 申通FeeModel
 * date: 2021-01-13 18:17:51
 * 
 * @author Ze.Wang
 * 
 */
public class ShentongFeeModel {
	private String startPrice;
	
	private String continuedHeavy;
	
	private String totalPrice;
	
	private String startWeight;
	
	private String continuedHeavyPrice;

	public String getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public String getContinuedHeavy() {
		return continuedHeavy;
	}

	public void setContinuedHeavy(String continuedHeavy) {
		this.continuedHeavy = continuedHeavy;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStartWeight() {
		return startWeight;
	}

	public void setStartWeight(String startWeight) {
		this.startWeight = startWeight;
	}

	public String getContinuedHeavyPrice() {
		return continuedHeavyPrice;
	}

	public void setContinuedHeavyPrice(String continuedHeavyPrice) {
		this.continuedHeavyPrice = continuedHeavyPrice;
	}

	@Override
	public String toString() {
		return "ShentongFeeModel [startPrice=" + startPrice + ", continuedHeavy=" + continuedHeavy + ", totalPrice="
				+ totalPrice + ", startWeight=" + startWeight + ", continuedHeavyPrice=" + continuedHeavyPrice + "]";
	}
	
}
