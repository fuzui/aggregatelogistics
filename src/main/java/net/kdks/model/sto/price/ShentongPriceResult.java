package net.kdks.model.sto.price;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.kdks.model.sto.ShentongBaseResult;

/**
 * 申通运费预估结果.
 * 
 * @author Ze.Wang
 * @since 0.0.7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShentongPriceResult extends ShentongBaseResult {
	
	private ShentongPriceData data;

}
