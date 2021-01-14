package net.kdks.model;

import lombok.Data;
import net.kdks.constant.ExpressResponseStatus;

/**
 * 响应结果
 * 
 * @author Ze.Wang
 * @since 0.0.1
 */
@Data
public class ExpressResponse<T> {

	/**
     * 授权响应状态码
     */
    private int code;

    /**
     * 授权响应信息
     */
    private String msg;

    /**
     * 授权响应数据，当且仅当 code = 2000 时返回
     */
    private T data;

	public ExpressResponse() {
		super();
	}

	public ExpressResponse(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	private static <T> ExpressResponse<T> restResult(T data, int code, String msg) {
		ExpressResponse<T> apiResult = new ExpressResponse<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
	public static <T> ExpressResponse<T> ok() {
		return restResult(null, ExpressResponseStatus.SUCCESS, null);
	}

	public static <T> ExpressResponse<T> ok(T data) {
		return restResult(data, ExpressResponseStatus.SUCCESS, null);
	}

	public static <T> ExpressResponse<T> ok(T data, String msg) {
		return restResult(data, ExpressResponseStatus.SUCCESS, msg);
	}

	public static <T> ExpressResponse<T> failed() {
		return restResult(null, ExpressResponseStatus.FAIL, null);
	}

	public static <T> ExpressResponse<T> failed(String msg) {
		return restResult(null, ExpressResponseStatus.FAIL, msg);
	}

	public static <T> ExpressResponse<T> failed(T data) {
		return restResult(data, ExpressResponseStatus.FAIL, null);
	}

	public static <T> ExpressResponse<T> failed(T data, String msg) {
		return restResult(data, ExpressResponseStatus.FAIL, msg);
	}
    
}
