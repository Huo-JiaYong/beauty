package org.yong.beauty.dto;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {
	private static final long serialVersionUID = 7431354778565785195L;

	private boolean success;

	private T data;

	private String error;

	public BaseResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public BaseResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "BaseResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}

}
