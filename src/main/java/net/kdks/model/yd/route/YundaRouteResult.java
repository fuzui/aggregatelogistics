package net.kdks.model.yd.route;

import java.util.List;
import lombok.Data;
import net.kdks.model.zto.ZhongtongTraceV1;

/**
 * 韵达轨迹查询数据.
 *
 * @author Ze.Wang
 * @since 0.0.9
 */
@Data
public class YundaRouteResult {
    /**
     * 路由信息.
     */
    private List<YundaRouteItem> steps;

    private Boolean result;
    /**
     * 快递号.
     */
    private String mailno;

    /**
     * 扫描类型.
     */
    private String scanType;

    /**
     * 扫描时间(ms).
     */
    private String time;

    private String remark;

    private String status;

}
