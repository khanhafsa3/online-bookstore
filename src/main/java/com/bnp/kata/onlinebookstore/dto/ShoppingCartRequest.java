package com.bnp.kata.onlinebookstore.dto;

import lombok.*;

@Getter
@Setter

public class ShoppingCartRequest {
    private Long cartItemId;
    private Long bookId;
    private Long userId;
    private int quantity;
	public Long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
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
	public ShoppingCartRequest(Long cartItemId, Long bookId, Long userId, int quantity) {
		this.cartItemId = cartItemId;
		this.bookId = bookId;
		this.userId = userId;
		this.quantity = quantity;
	}
	public ShoppingCartRequest() {;
    
}
}
