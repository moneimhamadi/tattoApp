package com.jwtproject.userSecurity.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Movement;
import com.jwtproject.userSecurity.Entity.Product;

@Service
public interface MovementService {

	public Movement getOneMovement(Long idMovement);
	public List <Movement> getAllMovementsByIdStock(Long idStock);

	public Movement supplyReceptionMovement(Movement movement, Long idStock);
	public List<Product> getAllProductsToDestruct(Long idStock);
	public List<Product> destructionMovement( Long idStock);
	public Movement internalProduction(Movement movement, Long idStock);
	public ResponseEntity movementStockToStock(Long idStockOne,Long idStockTwo,Map<String, Integer> barcodeQuantity);
}
