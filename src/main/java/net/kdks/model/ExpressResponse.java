package net.kdks.model;

import java.io.Serializable;

import net.kdks.constant.ExpressResponseStatus;

public class ExpressResponse<T> implements Serializable {
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
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

	@Override
	public String toString() {
		return "ExpressResponse [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
    
    
}
