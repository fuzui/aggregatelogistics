package net.kdks.model.htky;

import net.kdks.constant.BaishiScanType;
import net.kdks.constant.ShunfengOpCode;
import net.kdks.enums.ExpressStateEnum;
import net.kdks.model.ExpressData;
import net.kdks.utils.DateUtils;

/**
 * 流转信息项
 * @author wangze
 * @date 2020-10-10 19:08:36
 */
public class BaishiTraceItems extends ExpressData {
	
	private static final long serialVersionUID = 1L;

	public void setAcceptAddress(String acceptAddress) {
		super.setAreaName(acceptAddress);
	}
	
	public void setAcceptTime(String acceptTime) {
		
		super.setTime(DateUtils.strToTimestamp(acceptTime));
		super.setFtime(acceptTime);
	}
	
	public void setRemark(String remark) {
		super.setContext(remark);
	}
	
	public void setScanType(String scanType) {
		
		/**
		 * 已收件
		 */
		if(scanType.equals(BaishiScanType.COLLECTED)) {
			super.setStatus(ExpressStateEnum.COLLECTED.getValue());
			return;
		}
		/**
		 * 异常
		 */
		if(scanType.equals(BaishiScanType.COURIER_TAKE)
				|| scanType.equals(BaishiScanType.SECONDARY_PACKAGE)
				|| scanType.equals(BaishiScanType.BROKEN)
				|| scanType.equals(BaishiScanType.ADMIN_TAKE)
				|| scanType.equals(BaishiScanType.MODIFY_ADDRESS)) {
			super.setStatus(ExpressStateEnum.EXCEPTION.getValue());
			return;
		}
		/**
		 * 已签收
		 */
		if(scanType.equals(BaishiScanType.SIGNED) 
				|| scanType.equals(BaishiScanType.PICK)) {
			super.setStatus(ExpressStateEnum.SIGNED.getValue());
			return;
		}
		/**
		 * 快件已退回
		 */
		if(scanType.equals(BaishiScanType.BACK) || scanType.equals(BaishiScanType.REFUSE)) {
			super.setStatus(ExpressStateEnum.BACK.getValue());
			return;
		}
		/**
		 * 派送中
		 */
		if(scanType.equals(BaishiScanType.DELIVERING)) {
			super.setStatus(ExpressStateEnum.DELIVERING.getValue());
			return;
		}
		/**
		 * 代理收件
		 */
		if(scanType.equals(BaishiScanType.AGENT)
				|| scanType.equals(BaishiScanType.SPECIAL_SIGNED)
				|| scanType.equals(BaishiScanType.BAISHILINLI)
				|| scanType.equals(BaishiScanType.PUT_STORAGE)) {
			super.setStatus(ExpressStateEnum.AGENT.getValue());
			return;
		}
		/**
		 * 应客户要求,快件正在转寄中
		 */
		if(scanType.equals(BaishiScanType.FORWARD)) {
			super.setStatus(ExpressStateEnum.FORWARD.getValue());
			return;
		}
		super.setStatus(ExpressStateEnum.TRANSITING.getValue());
		
	}
	
	
	
}
