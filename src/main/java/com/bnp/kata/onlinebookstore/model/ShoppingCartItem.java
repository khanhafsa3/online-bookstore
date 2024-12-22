package com.bnp.kata.onlinebookstore.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	private Long userId;
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ShoppingCartItem(Long id, Book book, Long userId, int quantity) {

		this.id = id;
		this.book = book;
		this.userId = userId;
		this.quantity = quantity;
	}

	public ShoppingCartItem(Book book, Long userId, int quantity) {
		super();
		this.book = book;
		this.userId = userId;
		this.quantity = quantity;
	}

	public ShoppingCartItem() {

	};
}
