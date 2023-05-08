package com.jwtproject.userSecurity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtproject.userSecurity.Entity.Stock;
import com.jwtproject.userSecurity.Service.StockService;

@RestController
public class StockController {

	@Autowired
	StockService stockService;
	
	@PostMapping("/addStock")
	public Stock addStock(@RequestBody Stock s) {
		return stockService.addStock(s);
	}
	
	@GetMapping("/getOneStock/{idStock}")
	public Stock getOneStock (@PathVariable Long idStock) {
		return stockService.getOneStock(idStock);
	}
	
	@GetMapping("/getAllStocks")
	public List<Stock> getAllStocks() {
		return stockService.getAllStocks();
	}
	
	@PutMapping("/updateStock/{idStock}")
	public Stock updateStock(@PathVariable Long idStock,@RequestBody Stock stock) {
		return stockService.updateStock(stock, idStock);
	}
}
