package com.jwtproject.userSecurity.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jwtproject.userSecurity.Entity.Movement;
import com.jwtproject.userSecurity.Entity.Product;
import com.jwtproject.userSecurity.Service.MovementService;

@RestController
public class MovementController {
	
	@Autowired 
	MovementService movementService;
	
	@PostMapping("/saveMovementSupplyReception/{idStock}")
	public Movement saveMovementSypplyReception(@RequestBody Movement supplyReceptionMovement,@PathVariable Long idStock) {
		return movementService.supplyReceptionMovement(supplyReceptionMovement, idStock);
	}
	
	@PostMapping("/saveMovementDestruction/{idStock}")
	public List<Product> saveMovementDestruction(@PathVariable Long idStock) {
		return movementService.destructionMovement(idStock);
	}
	
	@GetMapping("/getAllProductsToDestruct/{idStock}")
	public List<Product> getAllProductsToDestruct(@PathVariable Long idStock) {
		return movementService.getAllProductsToDestruct(idStock);
	}
	@GetMapping("/getAllMovementByIdStock/{idStock}")
	public List<Movement> getAllMovementByIdStock(@PathVariable Long idStock) {
		return movementService.getAllMovementsByIdStock(idStock);
	}
	
	@PostMapping("/saveMovementInternalProduction/{idStock}")
	public Movement saveMovementInternalProduction(@RequestBody Movement internalProductionMovement,@PathVariable Long idStock) {
		return movementService.internalProduction(internalProductionMovement,idStock);
	}
	@PostMapping("/movementStockToStock/{idStockOne}/{idStockTwo}")
	public ResponseEntity<?> movementStockToStock(@PathVariable Long idStockOne,@PathVariable Long idStockTwo,@RequestBody  @JsonProperty("barcodeQuantity") Map<String, Integer> barcodeQuantity) {
		return movementService.movementStockToStock(idStockOne, idStockTwo, barcodeQuantity);
	}




}
