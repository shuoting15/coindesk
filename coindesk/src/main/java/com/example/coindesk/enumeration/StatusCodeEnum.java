package com.example.coindesk.enumeration;

public enum StatusCodeEnum {

	A0001("A0001", "%s成功!"),
	N1001("N1001", "查無此ID!"),
	N1002("N1002", "查無資料!"),
	N2001("N2001", "輸入錯誤!"),
	N9999("A9999", "發生錯誤!");

	private String code;
	private String message;

	StatusCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
	
	public String getMessage(String msg) {
		String newMsg = String.format(message, msg);
		return newMsg;
	}
	
}
