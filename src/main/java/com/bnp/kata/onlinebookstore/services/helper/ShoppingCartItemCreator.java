package com.bnp.kata.onlinebookstore.services.helper;

import org.springframework.stereotype.Component;

import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

@Component
public class ShoppingCartItemCreator {

	public ShoppingCartItem createCartItem(Book book, Long userId, int quantity) {
		return new ShoppingCartItem(book, userId, quantity);
	}

}
