package com.jwtproject.userSecurity.Entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boutique {
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long id;
	private String nameBoutique;
	private String adresse;
	private String city;
    private String phoneNumber;
	@JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    
	
	@OneToOne(mappedBy = "boutique", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Stock stock;
    
    
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
	public Boutique() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameBoutique() {
		return nameBoutique;
	}
	public void setNameBoutique(String nameBoutique) {
		this.nameBoutique = nameBoutique;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public Boutique(Long id, String nameBoutique, String adresse, String city, String phoneNumber, Date createdAt,
			Stock stock, Owner owner) {
		super();
		this.id = id;
		this.nameBoutique = nameBoutique;
		this.adresse = adresse;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.stock = stock;
		this.owner = owner;
	}
	
	
	
    
    


}
