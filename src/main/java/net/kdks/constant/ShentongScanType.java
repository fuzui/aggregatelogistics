package net.kdks.constant;

/**
 * 申通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ShentongScanType {
    /**
     * 装袋.
     */
    String TRUCK_LOADING = "装袋";
    /**
     * 到件.
     */
    String ARRIVED = "到件";
    /**
     * 发件.
     */
    String SENDING = "发件";
    /**
     * 派件.
     */
    String DELIVERING = "派件";
    /**
     * 收件.
     */
    String COLLECTED = "收件";

    /**
     * 客户签收.
     */
    String SIGNED = "客户签收";

    /**
     * 驿站代收.
     */
    String AGENT = "驿站代收";

}
