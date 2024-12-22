package com.bnp.kata.onlinebookstore.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK_STORE_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double totalPrice;
    private LocalDateTime orderDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public Order(Long id, Long userId, Double totalPrice, LocalDateTime orderDate) {
		
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}
	public Order() {
		
	}
	
}
