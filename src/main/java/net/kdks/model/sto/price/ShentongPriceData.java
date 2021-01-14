package net.kdks.model.sto.price;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 申通运费预估数据.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ShentongPriceData {
	@JSONField(name = "Ageing")
	private String ageing;
	
	@JSONField(name = "AvailableServiceItemList")
	private List<ShentongPriceDataItem> availableServiceItemList;

}
