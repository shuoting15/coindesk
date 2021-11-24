package com.example.coindesk.service.ipml;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coindesk.dao.CoinDao;
import com.example.coindesk.entity.CoinEntity;
import com.example.coindesk.enumeration.StatusCodeEnum;
import com.example.coindesk.parameter.Response;
import com.example.coindesk.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	private CoinDao coinDao;

	@Override
	public Response findById(Integer id) {
		Response response = new Response();
		if (!coinDao.findById(id).isPresent()) {
			response.setCode(StatusCodeEnum.N1001.getCode());
			response.setMessage(StatusCodeEnum.N1001.getMessage());
			response.setResult(null);
			return response;
		}
		CoinEntity coin = coinDao.findById(id).get();
		response.setCode(StatusCodeEnum.A0001.getCode());
		response.setMessage(StatusCodeEnum.A0001.getMessage("查詢"));
		response.setResult(coin);
		return response;
	}

	@Override
	public Response getAll() {
		Response response = new Response();
		List<CoinEntity> result = coinDao.findAll();
		if (result.size() <= 0) {
			response.setCode(StatusCodeEnum.N1002.getCode());
			response.setMessage(StatusCodeEnum.N1002.getMessage());
			response.setResult(null);
			return response;
		}
		response.setCode(StatusCodeEnum.A0001.getCode());
		response.setMessage(StatusCodeEnum.A0001.getMessage("查詢"));
		response.setResult(result);
		return response;
	}

	@Override
	public Response createCoin(CoinEntity coin) {
		Response response = new Response();
		// 判斷輸入
		if (coin.getChName() == null || coin.getEnName() == null) {
			response.setCode(StatusCodeEnum.N2001.getCode());
			response.setMessage(StatusCodeEnum.N2001.getMessage());
			return response;
		}
		// 新增
		CoinEntity rstCoin = coinDao.save(coin);
		response.setCode(StatusCodeEnum.A0001.getCode());
		response.setMessage(StatusCodeEnum.A0001.getMessage("新增"));
		response.setResult(rstCoin.getId());
		return response;
	}

	@Override
	public Response updateCoin(Integer id, CoinEntity coin) {
		Response response = new Response();
		// 判斷輸入
		if (coin.getChName() == null || coin.getEnName() == null) {
			response.setCode(StatusCodeEnum.N2001.getCode());
			response.setMessage(StatusCodeEnum.N2001.getMessage());
			return response;
		}
		Optional<CoinEntity> thisCoin = coinDao.findById(id);
		// 判斷id是否存在
		if (!thisCoin.isPresent()) {
			response.setCode(StatusCodeEnum.N1001.getCode());
			response.setMessage(StatusCodeEnum.N1001.getMessage());
			response.setResult(null);
			return response;
		}
		// 更新
		CoinEntity newCoin = thisCoin.get();
		newCoin.setChName(coin.getChName());
		newCoin.setEnName(coin.getEnName());
		try {
			CoinEntity updated = coinDao.save(newCoin);
			response.setCode(StatusCodeEnum.A0001.getCode());
			response.setMessage(StatusCodeEnum.A0001.getMessage("更新"));
			response.setResult(updated);
			return response;
		} catch (Exception e) {
			response.setCode(StatusCodeEnum.N9999.getCode());
			response.setMessage(StatusCodeEnum.N9999.getMessage());
			return response;
		}
	}

	@Override
	public Response deleteCoin(Integer id) {
		Response response = new Response();
		Optional<CoinEntity> rstCoin = coinDao.findById(id);
		// 判斷id是否存在
		if (!rstCoin.isPresent()) {
			response.setCode(StatusCodeEnum.N1001.getCode());
			response.setMessage(StatusCodeEnum.N1001.getMessage());
			response.setResult(null);
			return response;
		}
		// 刪除
		try {
			coinDao.deleteById(id);
			response.setCode(StatusCodeEnum.A0001.getCode());
			response.setMessage(StatusCodeEnum.A0001.getMessage("刪除"));
			response.setResult(null);
			return response;
		} catch (Exception e) {
			response.setCode(StatusCodeEnum.N9999.getCode());
			response.setMessage(StatusCodeEnum.N9999.getMessage());
			return response;
		}
	}

}
