package com.jwtproject.userSecurity.Entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nameClient;
	private String surnameClient;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservationDate;
	private Date createdAt;
	private boolean fCancelled;
	
	private Long quantityPrduct;
	private String barcodeProduct;
	private String nameProduct;
	private String descriptionProduct;
    private int reservationHour;
    private Long idBoutique;

	
//	@OneToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getSurnameClient() {
		return surnameClient;
	}
	public void setSurnameClient(String surnameClient) {
		this.surnameClient = surnameClient;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isfCancelled() {
		return fCancelled;
	}
	public void setfCancelled(boolean fCancelled) {
		this.fCancelled = fCancelled;
	}
	public Long getQuantity() {
		return quantityPrduct;
	}
	public void setQuantity(Long quantityPrduct) {
		this.quantityPrduct = quantityPrduct;
	}

	
	public Long getQuantityPrduct() {
		return quantityPrduct;
	}
	public void setQuantityPrduct(Long quantityPrduct) {
		this.quantityPrduct = quantityPrduct;
	}
	public String getBarcodeProduct() {
		return barcodeProduct;
	}
	public void setBarcodeProduct(String barcodeProduct) {
		this.barcodeProduct = barcodeProduct;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public String getDescriptionProduct() {
		return descriptionProduct;
	}
	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}
	
	public int getReservationHour() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.reservationDate);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
	public void setReservationHour(int reservationHour) {
	    this.reservationHour = reservationHour;
	}
	
	
	
	public Reservation(Long id, String nameClient, String surnameClient, Date reservationDate, Date createdAt,
			boolean fCancelled, Long quantityPrduct, String barcodeProduct, String nameProduct,
			String descriptionProduct, int reservationHour, Long idBoutique) {
		super();
		this.id = id;
		this.nameClient = nameClient;
		this.surnameClient = surnameClient;
		this.reservationDate = reservationDate;
		this.createdAt = createdAt;
		this.fCancelled = fCancelled;
		this.quantityPrduct = quantityPrduct;
		this.barcodeProduct = barcodeProduct;
		this.nameProduct = nameProduct;
		this.descriptionProduct = descriptionProduct;
		this.reservationHour = reservationHour;
		this.idBoutique = idBoutique;
	}
	public Long getIdBoutique() {
		return idBoutique;
	}
	public void setIdBoutique(Long idBoutique) {
		this.idBoutique = idBoutique;
	}
	public Reservation() {
		super();
	}
	public Reservation(Long id, String nameClient, String surnameClient, Date reservationDate, Date createdAt,
			boolean fCancelled, Long quantityPrduct, String barcodeProduct, String nameProduct,
			String descriptionProduct
			//, Product product
			) {
		super();
		this.id = id;
		this.nameClient = nameClient;
		this.surnameClient = surnameClient;
		this.reservationDate = reservationDate;
		this.createdAt = createdAt;
		this.fCancelled = fCancelled;
		this.quantityPrduct = quantityPrduct;
		this.barcodeProduct = barcodeProduct;
		this.nameProduct = nameProduct;
		this.descriptionProduct = descriptionProduct;
	}
	
	

	
	public Reservation(Date reservationDate, int reservationHour) {
        this.reservationDate = reservationDate;
        this.reservationHour = reservationHour;
    }

   
	
	
	
	
	
}
