package com.bnp.kata.onlinebookstore.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bnp.kata.onlinebookstore.exception.ItemNotFoundException;
import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.repository.BookRepository;
import com.bnp.kata.onlinebookstore.repository.ShoppingCartRepository;
import com.bnp.kata.onlinebookstore.services.helper.ShoppingCartItemCreator;

@Service
public class ShoppingCartService {

	private final ShoppingCartRepository cartRepository;
	private final BookRepository bookRepository;

	private final ShoppingCartItemCreator cartItemCreator;

	@Autowired
	public ShoppingCartService(ShoppingCartRepository cartRepository, BookRepository bookRepository,
			ShoppingCartItemCreator cartItemCreator) {
		this.cartRepository = cartRepository;
		this.bookRepository = bookRepository;
		this.cartItemCreator = cartItemCreator;
	}

	public ShoppingCartItem addBookToCart(Long bookId, Long userId, int quantity) {
		Book book = findBookById(bookId);
		ShoppingCartItem cartItem = cartItemCreator.createCartItem(book, userId, quantity);
		return cartRepository.save(cartItem);
	}

	private Book findBookById(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book not found"));
	}

}
