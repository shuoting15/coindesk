package com.example.coindesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coindesk.entity.CoinEntity;
import com.example.coindesk.enumeration.StatusCodeEnum;
import com.example.coindesk.parameter.Response;
import com.example.coindesk.service.CoinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/crud")
public class CoinController {

	@Autowired
	private CoinService coinService;

	/**
	 * Get coin by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/coins/{id}")
	public ResponseEntity<Response> getCoin(@PathVariable Integer id) {
		log.info("--Get COIN BY ID--");
		Response result = coinService.findById(id);
		if (StatusCodeEnum.N1001.getCode().equals(result.getCode())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * Get all coins
	 * 
	 * @return
	 */
	@GetMapping("/coins")
	public ResponseEntity<Response> getAll() {
		log.info("--Get ALL COINS--");
		Response result = coinService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * Create coin
	 * 
	 * @param coin
	 * @return
	 */
	@PostMapping("/coins")
	public ResponseEntity<Response> createCoin(@RequestBody CoinEntity coin) {
		log.info("--CREATE COIN--");
		Response result = coinService.createCoin(coin);
		if (StatusCodeEnum.N2001.getCode().equals(result.getCode())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	/**
	 * Update coin
	 * 
	 * @param id
	 * @param coin
	 * @return
	 */
	@PutMapping("/coins/{id}")
	public ResponseEntity<Response> updateCoin(@PathVariable Integer id, @RequestBody CoinEntity coin) {
		log.info("--UPDATE COIN--");
		Response result = coinService.updateCoin(id, coin);
		if (StatusCodeEnum.N2001.getCode().equals(result.getCode())
				|| StatusCodeEnum.N1001.getCode().equals(result.getCode())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * Delete coin
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/coins/{id}")
	public ResponseEntity<Response> deleteCoin(@PathVariable Integer id) {
		log.info("--DELETE COIN--");
		Response result = coinService.deleteCoin(id);
		if (StatusCodeEnum.N1001.getCode().equals(result.getCode())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
	}
}
