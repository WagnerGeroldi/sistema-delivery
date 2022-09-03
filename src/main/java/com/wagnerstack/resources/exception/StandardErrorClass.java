package com.wagnerstack.resources.exception;

import java.io.Serializable;

public class StandardErrorClass implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer status;
	String msg;
	Long timeStamp;

	public StandardErrorClass() {

	}

	public StandardErrorClass(Integer status, String msg, Long timeStamp) {

		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
