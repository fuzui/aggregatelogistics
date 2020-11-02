package net.kdks.model.yto;

import com.alibaba.fastjson.annotation.JSONField;

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
	
	public void setProcessInfo(String processInfo) {
		super.setContext(processInfo);
		/**
		 * 揽收
		 */
		if(processInfo.contains(YuantongScanType.COLLECTED)) {
			super.setStatus(ExpressStateEnum.COLLECTED.getValue());
			return;
		}
		/**
		 * 已签收
		 */
		if(processInfo.contains(YuantongScanType.SIGNED)) {
			super.setStatus(ExpressStateEnum.SIGNED.getValue());
			return;
		}
		/**
		 * 正在派送途中
		 */
		if(processInfo.contains(YuantongScanType.DELIVERING)) {
			super.setStatus(ExpressStateEnum.DELIVERING.getValue());
			return;
		}
		super.setStatus(ExpressStateEnum.TRANSITING.getValue());
		
	}

	@Override
	public String toString() {
		return "YuanTongResult [getContext()=" + getContext() + ", getTime()=" + getTime() + ", getFtime()="
				+ getFtime() + ", getStatus()=" + getStatus() + ", getAreaCode()=" + getAreaCode() + ", getAreaName()="
				+ getAreaName() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	
	
}
