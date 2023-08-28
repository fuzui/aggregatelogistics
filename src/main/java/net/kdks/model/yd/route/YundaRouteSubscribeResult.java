package net.kdks.model.yd.route;

import java.util.List;
import lombok.Data;

/**
 * 韵达轨迹订阅结果.
 *
 * @author Ze.Wang
 * @since 0.0.9
 */
@Data
public class YundaRouteSubscribeResult {
    /**
     * 路由信息.
     */
    private List<YundaRouteSubscribeItem> orders;

    private Boolean result;
}
