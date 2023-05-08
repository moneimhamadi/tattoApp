package com.jwtproject.userSecurity.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtproject.userSecurity.Entity.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
	public List<Movement> findAllByStockId(Long stockId);

}
