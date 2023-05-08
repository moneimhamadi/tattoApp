package com.jwtproject.userSecurity.Controller;

import com.jwtproject.userSecurity.Entity.Product;
import com.jwtproject.userSecurity.Repository.ProductRepository;
import com.jwtproject.userSecurity.Service.ProductService;
import com.jwtproject.userSecurity.Service.RoleService;
import com.jwtproject.userSecurity.Service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ProductController {
	
    
    private final ProductService service;
    private final ProductRepository proRep;
    

    @GetMapping("/getAllProducts")
    public List<Product> list() {
        return service.listAll();
    }
    @PostMapping("/addProduct")
    public void add(@RequestBody Product product) {
        service.save(product);
    }

    @GetMapping("/getOneProduct/{id}")
    public Product getOneProduct( @PathVariable Integer id) {
            return service.getOneProduct(id);
        
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
    
    
    //New Methods 
     @PostMapping("/saveProducts/{idStock}")
     public List<Product> saveProducts(@RequestBody List<Product> products,@PathVariable Long idStock){
    	 return service.saveProducts(products, idStock);
     }
     @GetMapping("/getProductByBarcodeAndExpiryDate/{barcode}/{expiryDate}/{idStock}")
     public Product getProductByBarcodeAndExpiryDate(@PathVariable String barcode,@PathVariable  @DateTimeFormat(pattern = "dd-MM-yyyy") Date expiryDate,@PathVariable Long idStock ) {
    	 return proRep.findByBarcodeAndExiryAtAndStockId(barcode, expiryDate,idStock);
     }
}
