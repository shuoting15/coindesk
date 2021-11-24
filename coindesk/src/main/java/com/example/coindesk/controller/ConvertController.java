package com.example.coindesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coindesk.parameter.Coin;
import com.example.coindesk.parameter.ConvertedInfo;
import com.example.coindesk.service.ConvertService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ConvertController {

	@Autowired
	private ConvertService convertService;

	/**
	 * Call coindesk API and convert data
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/coin")
	public ResponseEntity<ConvertedInfo> convertCoindesk() throws Exception {
		ConvertedInfo coinList = convertService.convertCoindesk();
		log.info("Get Coin Information Start...");
		return ResponseEntity.status(HttpStatus.OK).body(coinList);
	}

	/**
	 * Get data from coindesk API
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/coindesk")
	public ResponseEntity<Coin> getCoindesk() throws Exception {
		Coin coinInfo = convertService.getCoinDesk();
		log.info("Get Coindesk External API Start...");
		return ResponseEntity.status(HttpStatus.OK).body(coinInfo);
	}
}
