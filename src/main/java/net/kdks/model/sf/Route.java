package net.kdks.model.sf;

import lombok.ToString;
import net.kdks.constant.ShunfengOpCode;
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
public class Route extends ExpressData {
	
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
	
	public void setOpCode(String opCode) {
		/**
		 * 顺丰已收件
		 */
		if(opCode.equals(ShunfengOpCode.COLLECTED)) {
			super.setStatus(ExpressStateEnum.COLLECTED.getValue());
			return;
		}
		/**
		 * 异常
		 */
		if(opCode.equals(ShunfengOpCode.EXCEPTION)) {
			super.setStatus(ExpressStateEnum.EXCEPTION.getValue());
			return;
		}
		/**
		 * 已签收,感谢使用顺丰,期待再次为您服务
		 */
		if(opCode.equals(ShunfengOpCode.SIGNED) || opCode.equals(ShunfengOpCode.VIEW)) {
			super.setStatus(ExpressStateEnum.SIGNED.getValue());
			return;
		}
		/**
		 * 快件已退回/转寄,新单号为: XXX
		 */
		if(opCode.equals(ShunfengOpCode.BACK)) {
			super.setStatus(ExpressStateEnum.BACK.getValue());
			return;
		}
		/**
		 * 正在派送途中,请您准备签收(派件人:XXX,电话:XXX)
		 */
		if(opCode.equals(ShunfengOpCode.DELIVERING)) {
			super.setStatus(ExpressStateEnum.DELIVERING.getValue());
			return;
		}
		/**
		 * 代理收件
		 */
		if(opCode.equals(ShunfengOpCode.AGENT)) {
			super.setStatus(ExpressStateEnum.AGENT.getValue());
			return;
		}
		/**
		 * 应客户要求,快件正在转寄中
		 */
		if(opCode.equals(ShunfengOpCode.FORWARD)) {
			super.setStatus(ExpressStateEnum.FORWARD.getValue());
			return;
		}
		super.setStatus(ExpressStateEnum.TRANSITING.getValue());
		
	}
}
