package com.example.coindesk.parameter;

import java.util.Map;

import lombok.Data;

@Data
public class ConvertedInfo {

	private String updatedTime;
	private Map<String, ConvertedCoin> coinInfo;
}
