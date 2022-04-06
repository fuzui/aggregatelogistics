package net.kdks.model.sf;

import java.util.List;
import lombok.Data;

/**
 * 查询信心.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class MsgData {
    /**
     * 路由集.
     */
    private List<RouteResps> routeResps;

    /**
     * 单号集.
     */
    private List<WaybillNoInfo> waybillNoInfoList;

}
