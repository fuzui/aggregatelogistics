package net.kdks.enums;

/**
 * 快递查询状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public enum ExpressStateEnum implements ValueEnum<Integer> {

    /**
     * 暂无物流信息.
     */
    NO_INFO(-1),

    /**
     * 在途.
     */
    TRANSITING(0),
    /**
     * 揽收.
     */
    COLLECTED(1),
    /**
     * 疑难.
     */
    EXCEPTION(2),
    /**
     * 签收.
     */
    SIGNED(3),
    /**
     * 退回.
     */
    BACK(4),
    /**
     * 派件.
     */
    DELIVERING(5),
    /**
     * 代理.
     */
    AGENT(6),

    /**
     * 转投.
     */
    FORWARD(7);

    private final Integer value;

    ExpressStateEnum(Integer value) {
        this.value = value;
    }

    /**
     * Get enum value.
     *
     * @return enum value
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
