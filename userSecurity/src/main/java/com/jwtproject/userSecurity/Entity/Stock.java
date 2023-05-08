package com.jwtproject.userSecurity.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stock {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameStock;
    @JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Product> stockProducts;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")

    private Boutique boutique;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameStock() {
		return nameStock;
	}
	public void setNameStock(String nameStock) {
		this.nameStock = nameStock;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public List<Product> getStockProducts() {
		return stockProducts;
	}
	public void setStockProducts(List<Product> stockProducts) {
		this.stockProducts = stockProducts;
	}
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Boutique getBoutique() {
		return boutique;
	}
	public void setBoutique(Boutique boutique) {
		this.boutique = boutique;
	}
	public Stock(Long id, String nameStock, Date createdAt, List<Product> stockProducts, Boutique boutique) {
		super();
		this.id = id;
		this.nameStock = nameStock;
		this.createdAt = createdAt;
		this.stockProducts = stockProducts;
		this.boutique = boutique;
	}
	

	
	

}
