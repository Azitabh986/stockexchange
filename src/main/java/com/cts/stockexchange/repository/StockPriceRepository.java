package com.cts.stockexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.stockexchange.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

}
