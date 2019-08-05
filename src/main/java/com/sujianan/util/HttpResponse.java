package com.sujianan.util;

/**
 * 针对ajax请求封装的通用返回对象
 * @author	github: SuperInteface
 * @date	2019年7月26日
 * @param <T>
 */
public class HttpResponse<T> {
	private int status;
	private String message;
	private Object obj;

	public HttpResponse() {
		this.status = 1;
		this.message = "ok";
		this.obj = null;
	}

	public HttpResponse(int status, String message, Object obj) {
		this.status = status;
		this.message = message;
		this.obj = obj;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return this.obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}