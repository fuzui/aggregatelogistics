package net.kdks.constant;

/**
 * 京东路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.11
 */
public interface JingdongScanType {

    /**
     * 下单.
     */
    Integer ORDER = 100;

    /**
     * 快件揽收中.
     */
    Integer COLLECTING = 390;
    /**
     * 快件已揽收.
     */
    Integer COLLECTED = 420;

    /**
     * 运输中.
     */
    Integer TRANSPORTING = 430;

    /**
     * 派件.
     */
    Integer DELIVERING = 440;

    /**
     * 拒收.
     */
    Integer REJECTED = 530;

    /**
     * 已取消.
     */
    Integer CANCELLED = 690;

    /**
     * 退款.
     */
    Integer REFUND = 700;

}
