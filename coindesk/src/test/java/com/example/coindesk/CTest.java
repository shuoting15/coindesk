package com.example.coindesk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.coindesk.controller.CoinController;
import com.example.coindesk.parameter.Response;

@SpringBootTest
public class CTest {

	@Autowired
	private CoinController coinController;
	
	@Test
	void testCoin() {
		ResponseEntity<Response> a = coinController.getAll();
		System.out.println(a.getBody());
	}
}
