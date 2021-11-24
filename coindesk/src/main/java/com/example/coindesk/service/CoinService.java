package com.example.coindesk.service;

import com.example.coindesk.entity.CoinEntity;
import com.example.coindesk.parameter.Response;

public interface CoinService {

	public Response findById(Integer id);
	
	public Response getAll();

	public Response createCoin(CoinEntity coin);
	
	public Response updateCoin(Integer id, CoinEntity coin);
	
	public Response deleteCoin(Integer id);
}
