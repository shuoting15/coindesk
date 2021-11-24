package com.example.coindesk.service;

import com.example.coindesk.parameter.Coin;
import com.example.coindesk.parameter.ConvertedInfo;

public interface ConvertService {

	public ConvertedInfo convertCoindesk() throws Exception;
	
	public Coin getCoinDesk() throws Exception;
}
