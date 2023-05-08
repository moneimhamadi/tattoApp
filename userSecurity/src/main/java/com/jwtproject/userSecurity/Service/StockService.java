package com.jwtproject.userSecurity.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Stock;

@Service
public interface StockService {
	
	public Stock getOneStock(Long idStock);
	public Stock addStock(Stock stock);
	public List<Stock> getAllStocks();
	public Stock updateStock(Stock stock,Long idStock);
}
