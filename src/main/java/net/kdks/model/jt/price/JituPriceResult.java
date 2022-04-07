package net.kdks.model.jt.price;

import java.math.BigDecimal;
import lombok.Data;
import net.kdks.model.ExpressPriceResult;

/**
 * 极兔运费查询结果.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
@Data
public class JituPriceResult extends ExpressPriceResult {

    public void setTotalPrice(BigDecimal totalPrice) {
        super.setPrice(totalPrice.setScale(2));
    }
}
