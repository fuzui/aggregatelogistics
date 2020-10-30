package net.kdks.model.yto;

import net.kdks.constant.YuantongScanType;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.utils.DateUtils;
/**
 * 圆通返回结果
 * @author wangze
 * @date 2020-10-11 22:31:54
 */
public class YuanTongResult extends ExpressData {
	private static final long serialVersionUID = 1L;

	public void setWaybill_No(String waybillNo) {
		super.setAreaName(null);
	}
	
	public void setUpload_Time(String uploadTime) {
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
