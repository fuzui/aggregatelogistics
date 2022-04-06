package net.kdks.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 物品信息.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class CargoDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数量.
     */
    private BigDecimal count;

    /**
     * 单位.
     */
    private String unit;

    /**
     * 重量.
     */
    private BigDecimal weight;

    /**
     * 金额.
     */
    private BigDecimal amount;
    /**
     * 币种.
     */
    private String currency;

    /**
     * 名字.
     */
    private String name;

}
