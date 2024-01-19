package com.jbk.exceptions;

import java.util.Date;

public class CustomizeExceptionResponse {

	private String defaultMessage;
	private int statusCode;
	private Date date;
	
	public CustomizeExceptionResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
