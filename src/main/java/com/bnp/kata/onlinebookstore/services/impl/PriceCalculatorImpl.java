package com.bnp.kata.onlinebookstore.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

@Service
public class PriceCalculatorImpl implements PriceCalculator {

	@Override
	public Double calculateTotalPrice(List<ShoppingCartItem> cartItems) {
		return cartItems.stream().mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getBook().getPrice()).sum();
	}
}
