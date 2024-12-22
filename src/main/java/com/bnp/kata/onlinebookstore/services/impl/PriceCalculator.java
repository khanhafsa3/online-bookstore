package com.bnp.kata.onlinebookstore.services.impl;

import java.util.List;

import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

public interface PriceCalculator {

	Double calculateTotalPrice(List<ShoppingCartItem> cartItems);

}
