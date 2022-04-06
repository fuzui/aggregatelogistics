package net.kdks.constant;

/**
 * 中通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ZhongtongScanType {
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
    String SIGNED = "签收";
    /**
     * 第三方签收.
     */
    String THIRD_PARTY_SIGNED = "第三方签收";
    /**
     * 退件.
     */
    String BACK = "退件";
    /**
     * 退件签收.
     */
    String BACK_SIGNED = "退件签收";
    /**
     * 问题件.
     */
    String PROBLEM = "问题件";

    /**
     * 驿站代收.
     */
    String AGENT = "ARRIVAL";

    /**
     * 收件人已从第三方代理点签收.
     */
    String CLIENT_SIGNED = "SIGNED";

}
