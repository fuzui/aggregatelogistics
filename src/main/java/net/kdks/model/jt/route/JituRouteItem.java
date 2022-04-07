package net.kdks.model.jt.route;

import lombok.Data;
import net.kdks.constant.JituScanType;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.utils.DateUtils;

/**
 * 极兔路由轨迹信息.
 *
 * @author Ze.Wang
 * @since 0.0.8
 */
@Data
public class JituRouteItem extends ExpressData {
    public void setScanNetworkCity(String scanNetworkCity) {
        super.setAreaName(scanNetworkCity);
    }

    public void setScanTime(String scanTime) {
        super.setTime(DateUtils.strToTimestamp(scanTime));
        super.setFtime(scanTime);
    }

    public void setDesc(String desc) {
        super.setContext(desc);
    }

    /**
     * 处理路由状态.
     *
     * @param scanType 路由状态
     */
    public void setScanType(String scanType) {
        // 收件
        if (scanType.equals(JituScanType.COLLECTED)) {
            super.setStatus(ExpressStateEnum.COLLECTED.getValue());
            return;
        }
        // 已签收
        if (scanType.equals(JituScanType.SIGNED)
            || scanType.equals(JituScanType.OUT_STORAGE)) {
            super.setStatus(ExpressStateEnum.SIGNED.getValue());
            return;
        }
        // 代理收件
        if (scanType.equals(JituScanType.AGENT)) {
            super.setStatus(ExpressStateEnum.AGENT.getValue());
            return;
        }
        // 疑难
        if (scanType.equals(JituScanType.PROBLEM)) {
            super.setStatus(ExpressStateEnum.EXCEPTION.getValue());
            return;
        }

        super.setStatus(ExpressStateEnum.TRANSITING.getValue());
    }
    
}
