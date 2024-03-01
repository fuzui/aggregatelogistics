package net.kdks.model.jd.route;

import lombok.Data;
import net.kdks.constant.JingdongScanType;
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
public class JingdongRouteItem extends ExpressData {
    //    public void setScanNetworkCity(String scanNetworkCity) {
    //        super.setAreaName(scanNetworkCity);
    //    }

    /**
     * 运单号.
     */
    private String waybillCode;

    public void setPperationTime(String operationTime) {
        super.setTime(DateUtils.strToTimestamp(operationTime));
        super.setFtime(operationTime);
    }

    public void setOperationTitle(String operationTitle) {
        super.setContext(operationTitle);
    }

    /**
     * 处理路由状态.
     *
     * @param category 路由状态
     */
    public void setCategory(Integer category) {
        // 收件
        if (category.equals(JingdongScanType.COLLECTED)
            || category.equals(JingdongScanType.COLLECTING)
            || category.equals(JingdongScanType.ORDER)) {
            super.setStatus(ExpressStateEnum.COLLECTED.getValue());
            return;
        }

        if (category.equals(JingdongScanType.REJECTED)
            || category.equals(JingdongScanType.REFUND)) {
            super.setStatus(ExpressStateEnum.BACK.getValue());
            return;
        }

        if (category.equals(JingdongScanType.DELIVERING)) {
            super.setStatus(ExpressStateEnum.DELIVERING.getValue());
            return;
        }
        // 已签收
        // todo: 京东的已签收状态未知

        // 疑难
        if (category.equals(JingdongScanType.CANCELLED)) {
            super.setStatus(ExpressStateEnum.EXCEPTION.getValue());
            return;
        }

        super.setStatus(ExpressStateEnum.TRANSITING.getValue());
    }
    
}
