package com.jwtproject.userSecurity.Repository;

import com.jwtproject.userSecurity.Entity.Product;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product findByBarcodeAndExiryAtAndStockId(String barcode,Date exiryAt,Long stocId);
    public List<Product> findAllByStockIdAndExiryAtBetween(Long stockId, Date startDate, Date endDate);
    public List<Product> findAllByBarcode(String barcode);
    public Product findFirstByBarcode(String barcode);


}
