package com.isga.model;

import java.io.Serializable;

public class ErrorAPIResponse  extends Throwable implements Serializable {
	private static final long serialVersionUID = 1L;
	private int status;
	private String errorMessage;
	
	public ErrorAPIResponse() {
		super();
	}
	public ErrorAPIResponse(int status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
