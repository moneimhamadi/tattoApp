package com.jwtproject.userSecurity.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"stock"})
public class Movement  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
	private Date orderDate;
	private MovementType movType;
	
	@OneToMany(mappedBy = "movement", cascade = CascadeType.ALL)
    private List<ProductMovement> movementProducts;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;
	
	
	public Movement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Movement(long id, Date orderDate, MovementType movType, List<ProductMovement> movementProducts,
			Stock stock) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.movType = movType;
		this.movementProducts = movementProducts;
		this.stock = stock;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public MovementType getMovType() {
		return movType;
	}


	public void setMovType(MovementType movType) {
		this.movType = movType;
	}


	public List<ProductMovement> getMovementProducts() {
		return movementProducts;
	}


	public void setMovementProducts(List<ProductMovement> movementProducts) {
		this.movementProducts = movementProducts;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}



	
	

}
