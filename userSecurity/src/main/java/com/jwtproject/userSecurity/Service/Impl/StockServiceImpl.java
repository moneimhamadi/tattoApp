package com.jwtproject.userSecurity.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Stock;
import com.jwtproject.userSecurity.Repository.StockRepository;
import com.jwtproject.userSecurity.Service.StockService;


@Service
public class StockServiceImpl implements StockService {
@Autowired
StockRepository stockRepo;

@Override
public Stock addStock(Stock stock) {
	stock.setCreatedAt(new Date());
	return stockRepo.save(stock);
}

@Override
public Stock getOneStock(Long idStock) {
	
	return stockRepo.findById(idStock).get();
}

@Override
public List<Stock> getAllStocks() {
	
	return stockRepo.findAll();
}

@Override
public Stock updateStock(Stock stock, Long idStock) {
	Stock exisStock=stockRepo.findById(idStock).get();
	if (exisStock !=null) {
		exisStock.setNameStock(stock.getNameStock());
		return stockRepo.save(exisStock);
	}
	else
	return null;
}
	
	
}
