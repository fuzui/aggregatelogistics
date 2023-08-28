package net.kdks.constant;

/**
 * 韵达路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.9
 */
public interface YundaScanType {

    /**
     * 收件扫描.
     */
    String ACCEPT = "ACCEPT";

    /**
     * 已揽件.
     */
    String GOT = "GOT";

    /**
     * 入中转.
     */
    String ARRIVAL = "ARRIVAL";

    /**
     * 出中转.
     */
    String DEPARTURE = "DEPARTURE";

    /**
     * 派件中.
     */
    String SENT = "SENT";

    /**
     * 第三方代收入库.
     */
    String INBOUND = "INBOUND";

    /**
     * 已签收.
     */
    String SIGNED = "SIGNED";

    /**
     * 第三方代收快递员取出.
     */
    String OUTBOUND = "OUTBOUND";

    /**
     * 签收失败.
     */
    String SIGNFAIL = "SIGNFAIL";

    /**
     * 退回件.
     */
    String RETURN = "RETURN";

    /**
     * 问题件.
     */
    String ISSUE = "ISSUE";

    /**
     * 拒收.
     */
    String REJECTION = "REJECTION";

    /**
     * 其他.
     */
    String OTHER = "OTHER";

    /**
     * 入库扫描.
     */
    String OVERSEA_IN = "OVERSEA_IN";

    /**
     * 出库扫描.
     */
    String OVERSEA_OUT = "OVERSEA_OUT";

    /**
     * 清关开始.
     */
    String CLEARANCE_START = "CLEARANCE_START";

    /**
     * 清关结束.
     */
    String CLEARANCE_FINISH = "CLEARANCE_FINISH";

    /**
     * 清关失败.
     */
    String CLEARANCE_FAIL = "CLEARANCE_FAIL";

    /**
     * 干线到达.
     */
    String OVERSEA_ARRIVAL = "OVERSEA_ARRIVAL";

    /**
     * 干线离开.
     */
    String OVERSEA_DEPARTURE = "OVERSEA_DEPARTURE";

    /**
     * 转单.
     */
    String TRANSFER = "TRANSFER";

}
