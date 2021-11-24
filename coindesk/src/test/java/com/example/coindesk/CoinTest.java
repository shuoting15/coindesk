package com.example.coindesk;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.coindesk.entity.CoinEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class CoinTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetCoins() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/crud/coins"));
		resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		resultActions.andExpect(status().isOk())
		.andExpect(content().json("{'code': 'A0001','message': '查詢成功!','result': [{'id':1,'enName':'USD','chName':'美金'},{'id':2,'enName':'GBP','chName':'英鎊'},{'id':3,'enName':'EUR','chName':'歐元'}]}"))
		.andDo(print());
		String result = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("Test Result: " + result);
	}
	
	@Test
	void testCreateCoin() throws Exception {
		CoinEntity coin = new CoinEntity();
		coin.setChName("新臺幣");
		coin.setEnName("NTD");
		ObjectMapper mapper = new ObjectMapper();
		String request = mapper.writeValueAsString(coin);
		ResultActions resultActions = mockMvc.perform(post("/crud/coins")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request));
		resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		resultActions.andExpect(status().isCreated()).andDo(print());
	}
	
	@Test
	void testUpdateCoin() throws Exception {
		CoinEntity coin = new CoinEntity();
		coin.setChName("日圓");
		coin.setEnName("JPY");
		ObjectMapper mapper = new ObjectMapper();
		String request = mapper.writeValueAsString(coin);
		ResultActions resultActions = mockMvc.perform(put("/crud/coins/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request));
		resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		resultActions.andExpect(status().isOk()).andDo(print());
		String result = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("Test Result: " + result);
	}
	
	@Test
	void testDeleteCoin() throws Exception {
		ResultActions resultActions = mockMvc.perform(delete("/crud/coins/3"));
		resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
		resultActions.andExpect(status().isOk()).andDo(print());
	}
	
}
