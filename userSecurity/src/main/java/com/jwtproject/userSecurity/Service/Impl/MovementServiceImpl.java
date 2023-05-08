package com.jwtproject.userSecurity.Service.Impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Movement;
import com.jwtproject.userSecurity.Entity.MovementType;
import com.jwtproject.userSecurity.Entity.Product;
import com.jwtproject.userSecurity.Entity.ProductMovement;
import com.jwtproject.userSecurity.Entity.Stock;
import com.jwtproject.userSecurity.Repository.MovementRepository;
import com.jwtproject.userSecurity.Repository.ProductMovementRepository;
import com.jwtproject.userSecurity.Repository.ProductRepository;
import com.jwtproject.userSecurity.Repository.StockRepository;
import com.jwtproject.userSecurity.Service.MovementService;

@Service
public class MovementServiceImpl implements MovementService {

	@Autowired
	MovementRepository movementRepository;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	StockRepository stockRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProductMovementRepository prodcutMoevemnetRep;

	@Override
	public Movement supplyReceptionMovement(Movement movement, Long idStock) {
		movement.setMovType(MovementType.SUPPLY_RECEPTION);
		movement.setOrderDate(new Date());
		Stock stock = stockRepo.getById(idStock);
		movement.setStock(stock);

		List<ProductMovement> movementProducts = movement.getMovementProducts();

		for (ProductMovement movProduct : movementProducts) {
			movProduct.setStock(stock);
			movProduct.setMovement(movement);
			Product stockProduct = productRepo.findByBarcodeAndExiryAtAndStockId(movProduct.getBarcode(),
					movProduct.getExiryAt(), idStock);
			if (stockProduct != null) {
				Product existingProduct = stockProduct;
				existingProduct.setQuantity(existingProduct.getQuantity() + movProduct.getQuantity());
				productRepo.save(existingProduct);
			} else {
				movProduct.setStock(stock);
				movProduct.setMovement(movement);
				Product newProduct = modelMapper.map(movProduct, Product.class);
				newProduct.setStock(stock);
				stock.getStockProducts().add(newProduct);
				productRepo.save(newProduct);

			}
		}

		return movementRepository.save(movement);
	}

	public boolean productExistsInStock(Product p, Long idStock) {
		Stock myStock = stockRepo.findById(idStock).get();
		boolean exists = false;
		List<Product> stockProdcust = myStock.getStockProducts();
		for (Product stockProd : stockProdcust) {
			if (p.getExiryAt() == stockProd.getExiryAt() && p.getBarcode() == stockProd.getBarcode()) {
				exists = true;
			} else {
				exists = false;
			}
		}
		return exists;
	}

	@Override
	public Movement getOneMovement(Long idMovement) {
		return movementRepository.findById(idMovement).get();
	}

	@Override
	public List<Product> destructionMovement(Long idStock) {

		Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<Product> stockProducts = productRepo.findAllByStockIdAndExiryAtBetween(idStock, startDate, endDate);

		Movement movement = new Movement();
		movement.setMovType(MovementType.DESTRUCTION);
		movement.setOrderDate(new Date());
		movement.setStock(stockRepo.getById(idStock));
		for (Product productToDestroy : stockProducts) {
			productToDestroy.setQuantity(0);
			productToDestroy.setfExpired(true);
			productRepo.save(productToDestroy);
		}
		List<ProductMovement> destinationList = stockProducts.stream()
				.map(Product -> modelMapper.map(Product, ProductMovement.class)).collect(Collectors.toList());
		movement.setMovementProducts(destinationList);
		movementRepository.save(movement);
		return stockProducts;
	}

	@Override
	public List<Product> getAllProductsToDestruct(Long idStock) {
		Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<Product> stockProducts = productRepo.findAllByStockIdAndExiryAtBetween(idStock, startDate, endDate);
		return stockProducts;

	}

	@Override
	public Movement internalProduction(Movement movement, Long idStock) {
		movement.setMovType(MovementType.INTERNAL_PRODUCTION);
		movement.setOrderDate(new Date());
		Stock stock = stockRepo.getById(idStock);
		movement.setStock(stock);

		List<ProductMovement> movementProducts = movement.getMovementProducts();

		for (ProductMovement movProduct : movementProducts) {
			movProduct.setStock(stock);
			movProduct.setMovement(movement);
			Product stockProduct = productRepo.findByBarcodeAndExiryAtAndStockId(movProduct.getBarcode(),
					movProduct.getExiryAt(), idStock);
			if (stockProduct != null) {
				Product existingProduct = stockProduct;
				existingProduct.setQuantity(existingProduct.getQuantity() + movProduct.getQuantity());
				productRepo.save(existingProduct);
			} else {
				movProduct.setStock(stock);
				movProduct.setMovement(movement);
				Product newProduct = modelMapper.map(movProduct, Product.class);
				newProduct.setStock(stock);
				stock.getStockProducts().add(newProduct);
				productRepo.save(newProduct);

			}
		}

		return movementRepository.save(movement);
	}

	@Override
	public ResponseEntity movementStockToStock(Long idStockOne, Long idStockTwo, Map<String, Integer> barcodeQuantity) {
	    // Step 1: Check if both stocks exist, otherwise return an error
	    Optional<Stock> stockOneOptional = stockRepo.findById(idStockOne);
	    Optional<Stock> stockTwoOptional = stockRepo.findById(idStockTwo);

	    if (!stockOneOptional.isPresent() || !stockTwoOptional.isPresent()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock not found.");
	    }

	    Stock stockOne = stockOneOptional.get();
	    Stock stockTwo = stockTwoOptional.get();
	    
	    Movement movement = new Movement();
	    movement.setMovType(MovementType.STOCK_TO_STOCK);
	    movement.setStock(stockTwo);
	    movement.setOrderDate(new Date());
	    Movement savedMovement= movementRepository.save(movement);

	    // Step 2: Check if each product in barcodeQuantity exists in stockTwo,
	    // and if not, create a new product with the same quantity in stockOne and remove it from stockTwo
	    for (Map.Entry<String, Integer> entry : barcodeQuantity.entrySet()) {
	        String barcode = entry.getKey();
	        Integer quantity = entry.getValue();

	        Product productInStockOne = getProductByBarcode(barcode,idStockOne);

	        if (productInStockOne == null || productInStockOne.getQuantity() < quantity) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found or not enough quantity.");
	        }

	        Product productInStockTwo = getProductByBarcode(barcode,idStockTwo);

	        if (productInStockTwo == null) {
	            
	            Product newProduct = new Product(productInStockOne.getName(), productInStockOne.getDescription(), productInStockOne.getPrixUnitaire(), quantity, new Date(), productInStockOne.getExiryAt(), productInStockOne.isfExpired(),productInStockOne.getMaxQuantity(), productInStockOne.getMaxQuantity(), barcode, stockTwo);
	            stockTwo.getStockProducts().add(newProduct);
	        } else {
	            productInStockTwo.setQuantity(productInStockTwo.getQuantity() + quantity);
	        }

	        productInStockOne.setQuantity(productInStockOne.getQuantity() - quantity);
	        
	        ProductMovement movementProd = new ProductMovement();
	        movementProd.setQuantity(quantity);
	        movementProd.setBarcode(barcode);
	        movementProd.setStock(stockTwo);
	        movementProd.setExiryAt(productInStockOne.getExiryAt());
	        movementProd.setCreatedAt(new Date());
	        movementProd.setMaxQuantity(productInStockOne.getMaxQuantity());
	        movementProd.setMinQuantity(productInStockOne.getMinQuantity());
	        movementProd.setDescription(productInStockOne.getDescription());
	        movementProd.setName(productInStockOne.getName());
	        movementProd.setPrixUnitaire(productInStockOne.getPrixUnitaire());
	        movementProd.setMovement(savedMovement);
	        prodcutMoevemnetRep.save(movementProd);

	        // Step 4: Map and save the product movement using ModelMapper
	        
	    }

	    // Step 3: Save changes to stocks and return success
	    stockRepo.save(stockOne);
	    stockRepo.save(stockTwo);
	    
	
	    return ResponseEntity.ok("Stock movement completed successfully.");
	}
	
	public Product getProductByBarcode(String barcode, Long stockId) {
	    // Find the stock with the given id
	    Optional<Stock> stockOptional = stockRepo.findById(stockId);
	    if (!stockOptional.isPresent()) {
	        throw new RuntimeException("Stock not found");
	    }
	    Stock stock = stockOptional.get();
	    
	    // Iterate over the products in this stock and return the one with the given barcode
	    for (Product product : stock.getStockProducts()) {
	        if (product.getBarcode().equals(barcode)) {
	            return product;
	        }
	    }
	    return null; // Product not found
	}

	@Override
	public List<Movement> getAllMovementsByIdStock(Long idStock) {
		
		return movementRepository.findAllByStockId(idStock);
	}


}
