package net.kdks.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * 物流查询结果.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ExpressResult implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * -1 暂无物流信息 0在途，1揽收，2疑难，3签收，4退回，5派件，6转投，7转投
	 */
	private Integer state;
	/**
	 * 信息
	 */
	private String msg;
	/**
	 * 	是否签收标记
	 */
	private Integer ischeck;

	/**
	 * 	快递110中快递公司编码
	 */
	private String com;
	/**
	 * 快递单号
	 */
	private String nu;

	private List<ExpressData> data;
	
	private String originalResult;

	public static ExpressResult restResult(String msg) {
		ExpressResult apiResult = new ExpressResult();
		apiResult = JSONObject.parseObject(msg, ExpressResult.class);
		return apiResult;
	}
}
