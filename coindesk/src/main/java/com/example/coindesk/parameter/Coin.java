package com.example.coindesk.parameter;

import java.util.Map;

import lombok.Data;

@Data
public class Coin {

	private Map<String, Object> time;
	private String disclaimer;
	private String chartName;
	private Map<String, CoinDetail> bpi;
}
