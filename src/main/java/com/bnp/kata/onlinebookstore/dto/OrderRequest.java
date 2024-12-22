package com.bnp.kata.onlinebookstore.dto;

public class OrderRequest {
    private Long userId;

	public OrderRequest(Long userId) {
		super();
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
	public OrderRequest() {
		
	}
}
