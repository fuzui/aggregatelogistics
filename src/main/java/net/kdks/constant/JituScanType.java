package net.kdks.constant;

/**
 * 中通路由状态.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
public interface JituScanType {

    /**
     * 快件揽收.
     */
    String COLLECTED = "快件揽收";

    /**
     * 发件.
     */
    String SENDING = "发件扫描";

    /**
     * 到件.
     */
    String ARRIVED = "到件扫描";

    String OUT_WAREHOUSE = "出仓扫描";

    /**
     * 入库扫描.
     */
    String PUT_STORAGE = "入库扫描";

    /**
     * 代理点收入扫描.
     */
    String AGENT = "代理点收入扫描";

    /**
     * 快件取出扫描.
     */
    String OUT = "快件取出扫描";

    /**
     * 出库扫描.
     */
    String OUT_STORAGE = "出库扫描";

    /**
     * 客户签收.
     */
    String SIGNED = "快件签收";

    /**
     * 问题件.
     */
    String PROBLEM = "问题件扫描";

}
