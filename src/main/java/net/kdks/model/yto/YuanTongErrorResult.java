package net.kdks.model.yto;

import java.util.List;
import java.util.Map;

/**
 * 圆通轨迹查询错误信息.
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
public class YuanTongErrorResult {
	
	private Map<String,List> map;
	
	private String code;
	
	private String success;
	
	private String message;

	public Map<String, List> getMap() {
		return map;
	}

	public void setMap(Map<String, List> map) {
		this.map = map;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "YuanTongErrorResult [map=" + map + ", code=" + code + ", success=" + success + ", message=" + message
				+ "]";
	}
	
	
}
