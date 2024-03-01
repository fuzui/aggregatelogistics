package net.kdks.model.jd.route;

import java.util.List;
import lombok.Data;

/**
 * 极兔路由查询结果.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
@Data
public class JingdongRouteResult {
    /**
     * 快递单号.
     */
    private String billCode;

    /**
     * 路由轨迹信息.
     */
    private List<JingdongRouteItem> traceDetails;
}
