package net.kdks.model.yto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.ToString;
import net.kdks.constant.YuantongScanType;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.utils.DateUtils;

/**
 * 路由信息.
 *
 * @author Ze.Wang
 * @since 0.0.1
 */
@ToString
public class YuanTongResult extends ExpressData {
    private static final long serialVersionUID = 1L;

    @JSONField(name = "waybill_No")
    public void setWaybillNo(String waybillNo) {
        super.setAreaName(null);
    }

    @JSONField(name = "upload_Time")
    public void setUploadTime(String uploadTime) {
        super.setTime(DateUtils.strToTimestamp(uploadTime));
        super.setFtime(uploadTime);
    }

    /**
     * 处理路由信息.
     *
     * @param processInfo 路由信息
     */
    public void setProcessInfo(String processInfo) {
        super.setContext(processInfo);
        // 揽收
        if (processInfo.contains(YuantongScanType.COLLECTED)) {
            super.setStatus(ExpressStateEnum.COLLECTED.getValue());
            return;
        }
        // 已签收
        if (processInfo.contains(YuantongScanType.SIGNED)) {
            super.setStatus(ExpressStateEnum.SIGNED.getValue());
            return;
        }
        // 正在派送途中
        if (processInfo.contains(YuantongScanType.DELIVERING)) {
            super.setStatus(ExpressStateEnum.DELIVERING.getValue());
            return;
        }
        super.setStatus(ExpressStateEnum.TRANSITING.getValue());

    }

}
