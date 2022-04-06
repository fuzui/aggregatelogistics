package net.kdks.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运费预估入参.
 *
 * @author Ze.Wang
 * @since 0.0.7
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExpressPriceParam extends BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 起始省份.
     */
    private String startProvince;
    /**
     * 起始城市.
     */
    private String startCity;
    /**
     * 起始区.
     */
    private String startDistrict;
    /**
     * 起始详细地址.
     */
    private String startAddress;
    /**
     * 目的省份.
     */
    private String endProvince;
    /**
     * 目的城市.
     */
    private String endCity;
    /**
     * 起始区.
     */
    private String endDistrict;
    /**
     * 起始详细地址.
     */
    private String endAddress;
    /**
     * 重量.
     */
    private BigDecimal weight;
    /**
     * 长度.
     */
    private BigDecimal length;
    /**
     * 宽度.
     */
    private BigDecimal width;
    /**
     * 高度.
     */
    private BigDecimal height;

}