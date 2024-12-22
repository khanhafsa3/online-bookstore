package com.bnp.kata.onlinebookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnp.kata.onlinebookstore.dto.ShoppingCartRequest;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.services.ShoppingCartService;

@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping("/add")
	public ResponseEntity<ShoppingCartItem> addBookToCart(@RequestBody ShoppingCartRequest cartRequest) {
		ShoppingCartItem addedItem = shoppingCartService.addBookToCart(cartRequest.getBookId(), cartRequest.getUserId(),
				cartRequest.getQuantity());
		return ResponseEntity.ok(addedItem);
	}
	 
}
