package net.kdks.model.yd.route;

import lombok.Data;
import net.kdks.model.ExpressRouteSubscribeData;

/**
 * 韵达路由轨迹信息.
 *
 * @author Ze.Wang
 * @since 0.0.9
 */
@Data
public class YundaRouteSubscribeItem extends ExpressRouteSubscribeData {
    @Override
    public void setResult(Boolean result) {
        super.setResult(result);
    }

    public void setOrderid(String orderid) {
        super.setExpressNo(orderid);
    }
}
