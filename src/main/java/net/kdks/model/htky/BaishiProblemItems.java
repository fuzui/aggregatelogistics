package net.kdks.model.htky;

import lombok.Data;

/**
 * 问题件子项.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class BaishiProblemItems {
	/**
	 * 序列号
	 */
	private Long seqNum;
	/**
	 * 	问题件类型，包括：
	 * 应到未到件、非法面单、无头件、被污染件、双面单、退件、
	 * 违规包装、其他、学校件、面单信息遗失无法正常投递、查无此人、
	 * 超大超长超重、节假日无人收货、快件遗失、代收结算问题、面单破损、
	 * 大笔错写、地址不详、更改派送地址、客户要求延迟派送、超区件、内部运力不足、
	 * 联系不上收件人、分拣差错、 拒付到付款、客户拒收、快件破损、违禁品、
	 * 未带/少带派送费、自提件、到付结算问题
	 */
	private String problemType;
	/**
	 * 	登记人
	 */
	private String registerMan;
	/**
	 * 登记日期 (通过“yyyy-MM-dd HH:mm:ss”格式化)
	 */
	private String registerDate;
	/**
	 * 登记站点
	 */
	private String registerSite;
	/**
	 * 	问题件类型
	 */
	private String problemCause;
	/**
	 * 通知站点
	 */
	private String noticeSite;
	/**
	 * 	回复人
	 */
	private String replyMan;
	/**
	 * 回复日期 (通过“yyyy-MM-dd HH:mm:ss”格式化)
	 */
	private String replyDate;
	/**
	 * 回复内容
	 */
	private String replyContent;
	
}
