package com.jwtproject.userSecurity.Service;

import com.jwtproject.userSecurity.Entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

	public List<Product> listAll();

	public void save(Product product);

	public Product getOneProduct(Integer id);

	public void delete(Integer id);

	public List<Product> saveProducts(List<Product> productsToAdd,Long idStock );
	
	

}
