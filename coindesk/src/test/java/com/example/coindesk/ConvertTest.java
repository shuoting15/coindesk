package com.example.coindesk;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest
public class ConvertTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test API convert coin
	 * 
	 * @throws Exception
	 */
	@Test
	void testCoinDeskConverted() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/coin"));
		resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		resultActions.andDo(print()).andExpect(status().isOk());
		String result = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("Test Result: " + result);
	}

	/**
	 * Test API get data from coindesk
	 * 
	 * @throws Exception
	 */
	@Test
	void testCoinDesk() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/coindesk"));
		resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		resultActions.andExpect(status().isOk()).andDo(print());
		String result = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("Test Result: " + result);
	}
}
