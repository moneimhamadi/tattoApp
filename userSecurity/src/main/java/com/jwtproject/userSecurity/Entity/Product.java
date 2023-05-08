package com.jwtproject.userSecurity.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private double prixUnitaire;
	private int quantity;
    @JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;
    @Temporal(TemporalType.DATE)
    private Date exiryAt;
	private boolean fExpired;
	private int maxQuantity;
	private int minQuantity;
	private String barcode;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id")
	private Stock stock;
//	@OneToOne(mappedBy = "product")
//    private Reservation reservation;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getExiryAt() {
		return exiryAt;
	}
	public void setExiryAt(Date exiryAt) {
		this.exiryAt = exiryAt;
	}
	public boolean isfExpired() {
		return fExpired;
	}
	public void setfExpired(boolean fExpired) {
		this.fExpired = fExpired;
	}
	public int getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
//	public Reservation getReservation() {
//		return reservation;
//	}
//	public void setReservation(Reservation reservation) {
//		this.reservation = reservation;
//	}
	public Product(String name, String description, double prixUnitaire, int quantity, Date createdAt, Date exiryAt,
			boolean fExpired, int maxQuantity, int minQuantity, String barcode, Stock stock) {
		super();
		this.name = name;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.exiryAt = exiryAt;
		this.fExpired = fExpired;
		this.maxQuantity = maxQuantity;
		this.minQuantity = minQuantity;
		this.barcode = barcode;
		this.stock = stock;
	}
	
	public Product(Integer id, String name, String description, double prixUnitaire, int quantity, Date createdAt,
			Date exiryAt, boolean fExpired, int maxQuantity, int minQuantity, String barcode, Stock stock
			
			//,Reservation reservation
			) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.exiryAt = exiryAt;
		this.fExpired = fExpired;
		this.maxQuantity = maxQuantity;
		this.minQuantity = minQuantity;
		this.barcode = barcode;
		this.stock = stock;
		//this.reservation = reservation;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	


	
	
	
}
