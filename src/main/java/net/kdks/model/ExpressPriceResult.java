package net.kdks.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 运费估算结果.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
@Data
public class ExpressPriceResult implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 时效.
     */
    private BigDecimal time;
    /**
     * 邮费.
     */
    private BigDecimal price;

}
