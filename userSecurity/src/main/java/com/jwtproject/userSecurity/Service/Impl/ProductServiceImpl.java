package com.jwtproject.userSecurity.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Repository.ProductRepository;
import com.jwtproject.userSecurity.Repository.StockRepository;
import com.jwtproject.userSecurity.Service.ProductService;
import com.jwtproject.userSecurity.Entity.Product;
import com.jwtproject.userSecurity.Entity.Stock;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repo;
	@Autowired
	StockRepository stockRepo;

	@Override
	public List<Product> listAll() {
		return repo.findAll();
	}

	@Override
	public void save(Product product) {
		repo.save(product);
	}

	@Override
	public Product getOneProduct(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<Product> saveProducts(List<Product> productsToAdd, Long idStock) {
	    List<Product> distinctProducts = new ArrayList<>();
	    for (Product product : productsToAdd) {
	        boolean isProductFound = false;
	        Product existingProduct = repo.findByBarcodeAndExiryAtAndStockId(product.getBarcode(), product.getExiryAt(), idStock);
	        if (existingProduct != null) {
	            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
	            distinctProducts.add(existingProduct);
	            isProductFound = true;
	        }
	        for (Product p : distinctProducts) {
	            if (p.getBarcode().equals(product.getBarcode()) && p.getExiryAt().equals(product.getExiryAt())) {
	                p.setQuantity(p.getQuantity() + product.getQuantity());
	                isProductFound = true;
	                break;
	            }
	        }
	        if (!isProductFound) {
	            distinctProducts.add(product);
	        }
	    }
	    Stock c = stockRepo.findById(idStock).get();

	    for (Product p : distinctProducts) {
	        p.setStock(c);
	        p.setCreatedAt(new Date());
	    }
	    return repo.saveAll(distinctProducts);
	}
	
	
}
