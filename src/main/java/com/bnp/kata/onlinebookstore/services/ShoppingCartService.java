package com.bnp.kata.onlinebookstore.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.kata.onlinebookstore.dto.ShoppingCartRequest;
import com.bnp.kata.onlinebookstore.exception.ItemNotFoundException;
import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.repository.BookRepository;
import com.bnp.kata.onlinebookstore.repository.ShoppingCartRepository;
import com.bnp.kata.onlinebookstore.services.helper.ShoppingCartItemCreator;
import com.bnp.kata.onlinebookstore.services.validator.ShoppingCartItemValidator;

@Service
public class ShoppingCartService {

	private final ShoppingCartRepository cartRepository;
	private final BookRepository bookRepository;
    private final ShoppingCartItemValidator cartItemValidator;
	private final ShoppingCartItemCreator cartItemCreator;

	@Autowired
	public ShoppingCartService(ShoppingCartRepository cartRepository, BookRepository bookRepository,
			ShoppingCartItemCreator cartItemCreator,ShoppingCartItemValidator cartItemValidator) {
		this.cartRepository = cartRepository;
		this.bookRepository = bookRepository;
		this.cartItemCreator = cartItemCreator;
		this.cartItemValidator = cartItemValidator;
	}

	public ShoppingCartItem addBookToCart(Long bookId, Long userId, int quantity) {
		Book book = findBookById(bookId);
		ShoppingCartItem cartItem = cartItemCreator.createCartItem(book, userId, quantity);
		return cartRepository.save(cartItem);
	}
    public ShoppingCartItem updateBookQuantity(ShoppingCartRequest cartRequest) {
        ShoppingCartItem cartItem = findCartItemById(cartRequest.getCartItemId());
        cartItemValidator.validateUserAuthorization(cartItem, cartRequest.getUserId());

        cartItem.setQuantity(cartRequest.getQuantity());
        return cartRepository.save(cartItem);
    }
    private ShoppingCartItem findCartItemById(Long cartItemId) {
        return cartRepository.findById(cartItemId)
                .orElseThrow(() -> new ItemNotFoundException("Item not found in cart"));
    }
	private Book findBookById(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book not found"));
	}

}
