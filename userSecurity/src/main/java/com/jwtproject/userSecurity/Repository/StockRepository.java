package com.jwtproject.userSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtproject.userSecurity.Entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
	public Optional<Stock> findById(Long id);

}
