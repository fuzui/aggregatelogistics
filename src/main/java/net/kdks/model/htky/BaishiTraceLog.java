package net.kdks.model.htky;
/**
 * 百世流转信息
 * @author wangze
 * @date 2020-10-10 19:04:59
 */
public class BaishiTraceLog {
	/**
	 * 抽检标记
	 */
	private Boolean check;
	/**
	 * 问题件
	 */
	private BaishiProblem problems;
	/**
	 * 运单号
	 */
	private String mailNo;
	/**
	 * 流转信息
	 */
	private BaishiTrace traces;
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public BaishiProblem getProblems() {
		return problems;
	}
	public void setProblems(BaishiProblem problems) {
		this.problems = problems;
	}
	public String getMailNo() {
		return mailNo;
	}
	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}
	public BaishiTrace getTraces() {
		return traces;
	}
	public void setTraces(BaishiTrace traces) {
		this.traces = traces;
	}
	@Override
	public String toString() {
		return "BaishiTraceLog [check=" + check + ", problems=" + problems + ", mailNo=" + mailNo + ", traces=" + traces
				+ "]";
	}
	
	
}
