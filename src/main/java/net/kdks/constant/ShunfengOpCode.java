package net.kdks.constant;

/**
 * 顺丰路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface ShunfengOpCode {
    /**
     * 快件在【XXX营业点】已装车,准备发往 【XXX集散中心】.
     */
    String TRUCK_LOADING = "30";
    /**
     * 快件到达 【XXX集散中心】.
     */
    String ARRIVED = "31";
    /**
     * 派件异常原因.
     */
    String EXCEPTION = "33";
    /**
     * 快件在XXX ,准备送往下一站.
     */
    String READY_NEXT = "3036";
    /**
     * 正在派送途中,请您准备签收(派件人:XXX,电话:XXX).
     */
    String DELIVERING = "44";
    /**
     * 顺丰已收件.
     */
    String COLLECTED = "50";
    /**
     * 由于XXX原因 派件不成功.
     */
    String FAILED = "70";
    /**
     * 已签收,感谢使用顺丰,期待再次为您服务.
     */
    String SIGNED = "80";
    /**
     * 在官网"运单资料&签收图",可查看签收人信.
     */
    String VIEW = "8000";
    /**
     * 快件到达顺丰店/站.
     */
    String ARRIVED_STORE = "130";
    /**
     * 快件正送往顺丰店/站.
     */
    String DELIVERING_STORE = "123";
    /**
     * 代理收件.
     */
    String AGENT = "607";
    /**
     * 应客户要求,快件正在转寄中.
     */
    String FORWARD = "99";
    /**
     * 快件已退回/转寄,新单号为: XXX.
     */
    String BACK = "648";
}
