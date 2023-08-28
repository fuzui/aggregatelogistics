package net.kdks.model.yd.route;

import lombok.Data;
import net.kdks.constant.JituScanType;
import net.kdks.constant.YundaScanType;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.utils.DateUtils;

/**
 * 韵达路由轨迹信息.
 *
 * @author Ze.Wang
 * @since 0.0.9
 */
@Data
public class YundaRouteItem extends ExpressData {
    public void setCity(String city) {
        super.setAreaName(city);
    }

    public void setTime(String time) {
        super.setTime(DateUtils.strToTimestamp(time));
        super.setFtime(time);
    }

    public void setDescription(String description) {
        super.setContext(description);
    }

    /**
     * 处理路由状态.
     *
     * @param action 路由状态
     */
    public void setAction(String action) {
        // 收件
        if (action.equals(YundaScanType.GOT) || action.equals(YundaScanType.ACCEPT)) {
            super.setStatus(ExpressStateEnum.COLLECTED.getValue());
            return;
        }
        // 已签收
        if (action.equals(YundaScanType.SIGNED)
            || action.equals(YundaScanType.OUTBOUND)) {
            super.setStatus(ExpressStateEnum.SIGNED.getValue());
            return;
        }
        // 退货
        if (action.equals(YundaScanType.RETURN) || action.equals(YundaScanType.REJECTION)) {
            super.setStatus(ExpressStateEnum.BACK.getValue());
            return;
        }

        // 问题件
        if (action.equals(YundaScanType.ISSUE) || action.equals(YundaScanType.SIGNFAIL)) {
            super.setStatus(ExpressStateEnum.EXCEPTION.getValue());
            return;
        }

        // 派件
        if (action.equals(YundaScanType.SENT)) {
            super.setStatus(ExpressStateEnum.DELIVERING.getValue());
            return;
        }

        // 代理
        if (action.equals(YundaScanType.INBOUND)) {
            super.setStatus(ExpressStateEnum.AGENT.getValue());
            return;
        }

        // 转投
        if (action.equals(YundaScanType.TRANSFER)) {
            super.setStatus(ExpressStateEnum.FORWARD.getValue());
            return;
        }

        super.setStatus(ExpressStateEnum.TRANSITING.getValue());
    }
    
}
