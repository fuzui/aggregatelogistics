package net.kdks.constant;

/**
 * 百世路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface BaishiScanType {
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
     * 代理点签收.
     */
    String AGENT = "代理点签收";
    /**
     * 特殊签收.
     */
    String SPECIAL_SIGNED = "特殊签收";
    /**
     * 百世邻里.
     */
    String BAISHILINLI = "百世邻里";
    /**
     * 入库/入柜.
     */
    String PUT_STORAGE = "入库/入柜";
    /**
     * 用户提货.
     */
    String PICK = "用户提货";

    /**
     * 转同行.
     */
    String FORWARD = "转同行";
    /**
     * 快递员取出包裹.
     */
    String COURIER_TAKE = "快递员取出包裹";
    /**
     * 客户拒收.
     */
    String REFUSE = "客户拒收";
    /**
     * 二次派件.
     */
    String SECONDARY_PACKAGE = "二次派件";
    /**
     * 货物破损.
     */
    String BROKEN = "货物破损";
    /**
     * 退件.
     */
    String BACK = "退件";
    /**
     * 管理员取出包裹.
     */
    String ADMIN_TAKE = "管理员取出包裹";
    /**
     * 更改派送地址.
     */
    String MODIFY_ADDRESS = "更改派送地址";
}
