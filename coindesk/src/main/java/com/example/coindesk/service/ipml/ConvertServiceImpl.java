package com.example.coindesk.service.ipml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.coindesk.dao.CoinDao;
import com.example.coindesk.entity.CoinEntity;
import com.example.coindesk.parameter.Coin;
import com.example.coindesk.parameter.ConvertedCoin;
import com.example.coindesk.parameter.ConvertedInfo;
import com.example.coindesk.service.ConvertService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConvertServiceImpl implements ConvertService {

	@Autowired
	private CoinDao coinDao;

	/**
	 * https://api.coindesk.com/v1/bpi/currentprice.json
	 */
	private final WebClient webClient = WebClient.builder().baseUrl("https://api.coindesk.com").build();

	public String getCoinDeskAPI() throws Exception {
		return webClient.get().uri("/v1/bpi/currentprice.json").retrieve().bodyToMono(String.class).block();
	}

	@Override
	public ConvertedInfo convertCoindesk() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String data = getCoinDeskAPI();

		// json data to object
		Coin coin = mapper.readValue(data, Coin.class);

		// 帶T時間轉date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date parse = simpleDateFormat.parse((String) coin.getTime().get("updatedISO"));

		// date轉需要的時間格式字串
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateToStr = dateFormat.format(parse);

		ConvertedInfo convertedInfo = new ConvertedInfo();
		convertedInfo.setUpdatedTime(dateToStr);

		// 取得各幣別
		Map<String, ConvertedCoin> getCoindesk = new HashMap<>();
		for (String eachCoin : coin.getBpi().keySet()) {
			// 組成回傳所需幣別,中文名稱,匯率
			ConvertedCoin convertedCoin = new ConvertedCoin();
			convertedCoin.setEnName(eachCoin);
			convertedCoin.setRate(coin.getBpi().get(eachCoin).getRate_float());
			getCoindesk.put(eachCoin, convertedCoin);
			// 取得中文名稱
			CoinEntity item = coinDao.findByEnName(eachCoin);
			if (item != null) {
				convertedCoin.setChName(item.getChName());
			} else {
				convertedCoin.setChName("無對應名稱");
			}
		}
		convertedInfo.setCoinInfo(getCoindesk);
		return convertedInfo;
	}

	@Override
	public Coin getCoinDesk() throws Exception {
		String data = getCoinDeskAPI();
		ObjectMapper mapper = new ObjectMapper();
		Coin coin = mapper.readValue(data, Coin.class);
		return coin;
	}

}
