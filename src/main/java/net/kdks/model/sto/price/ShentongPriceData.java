package net.kdks.model.sto.price;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 申通运单数据.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class ShentongPriceData {
	@JSONField(name = "Ageing")
	private String ageing;
	
	@JSONField(name = "AvailableServiceItemList")
	private List<ShentongPriceDataItem> availableServiceItemList;

	public String getAgeing() {
		return ageing;
	}

	public void setAgeing(String ageing) {
		this.ageing = ageing;
	}

	public List<ShentongPriceDataItem> getAvailableServiceItemList() {
		return availableServiceItemList;
	}

	public void setAvailableServiceItemList(List<ShentongPriceDataItem> availableServiceItemList) {
		this.availableServiceItemList = availableServiceItemList;
	}

	@Override
	public String toString() {
		return "ShentongPriceData [ageing=" + ageing + ", availableServiceItemList=" + availableServiceItemList + "]";
	}

}
