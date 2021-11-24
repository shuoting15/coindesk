package com.example.coindesk.parameter;

import lombok.Data;

@Data
public class Response {
	
	private String code;
    private String message;
    private Object result;
}