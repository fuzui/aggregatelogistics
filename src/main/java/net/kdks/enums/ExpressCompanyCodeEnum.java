package net.kdks.enums;

/**
 * 快递公司编码.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public enum ExpressCompanyCodeEnum implements ValueEnum<String> {

    /**
     * 顺丰.
     */
    SF("SF"),
    /**
     * 圆通.
     */
    YTO("YTO"),
    /**
     * 申通.
     */
    STO("STO"),
    /**
     * 中通.
     */
    ZTO("ZTO"),
    /**
     * 韵达.
     */
    YD("YD"),
    /**
     * 百世.
     */
    HTKY("HTKY"),
    /**
     * 邮政.
     */
    YZPY("YZPY"),

    /**
     * 德邦.
     */
    DBL("DBL");

    private final String value;

    ExpressCompanyCodeEnum(String value) {
        this.value = value;
    }

    /**
     * Get enum value.
     *
     * @return enum value
     */
    @Override
    public String getValue() {
        return value;
    }
}
