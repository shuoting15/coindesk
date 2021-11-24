package com.example.coindesk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coindesk.entity.CoinEntity;

public interface CoinDao extends JpaRepository<CoinEntity, Integer> {

	CoinEntity findByEnName(String enName);
}
