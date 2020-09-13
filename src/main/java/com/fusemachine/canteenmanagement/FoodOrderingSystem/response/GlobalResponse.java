package com.fusemachine.canteenmanagement.FoodOrderingSystem.response;

public class GlobalResponse {
	private String msg;
	private int statusCode;
	private long timeStamp;

	
	public GlobalResponse(String msg, int statusCode, long timeStamp) {
		this.msg = msg;
		this.statusCode = statusCode;
		this.timeStamp = timeStamp;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
