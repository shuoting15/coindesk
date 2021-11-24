package com.example.coindesk.parameter;

import lombok.Data;

@Data
public class CoinDetail {

	private String code;
	private String symbol;
	private String rate;
	private String description;
	private Double rate_float;
    
}
