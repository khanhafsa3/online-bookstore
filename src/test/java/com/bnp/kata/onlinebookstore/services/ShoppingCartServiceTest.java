package com.bnp.kata.onlinebookstore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bnp.kata.onlinebookstore.dto.ShoppingCartRequest;
import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.repository.BookRepository;
import com.bnp.kata.onlinebookstore.repository.ShoppingCartRepository;
import com.bnp.kata.onlinebookstore.services.helper.ShoppingCartItemCreator;
import com.bnp.kata.onlinebookstore.services.validator.ShoppingCartItemValidator;

public class ShoppingCartServiceTest {
	 @Mock
private ShoppingCartItemValidator cartItemValidator;
	@Mock
	private ShoppingCartRepository cartRepository;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private ShoppingCartItemCreator cartItemCreator;

	@InjectMocks
	private ShoppingCartService shoppingCartService;

	private Book book;
	private ShoppingCartItem cartItem;
	private ShoppingCartRequest cartRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		book = new Book();
		book.setId(1L);
		book.setTitle("Test Book");
		book.setPrice(20.0);
		cartItem = new ShoppingCartItem();
		cartItem.setId(1L);
		cartItem.setBook(book);
		cartItem.setUserId(1L);
		cartItem.setQuantity(2);
		cartRequest = new ShoppingCartRequest();
		cartRequest.setCartItemId(1L);
		cartRequest.setUserId(1L);
		cartRequest.setQuantity(3);
	}

	@Test
	void testAddBookToCart() {
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
		when(cartItemCreator.createCartItem(any(), anyLong(), anyInt())).thenReturn(cartItem);
		when(cartRepository.save(any())).thenReturn(cartItem);
		ShoppingCartItem addedItem = shoppingCartService.addBookToCart(1L, 1L, 2);
		assertNotNull(addedItem);
		assertEquals(2, addedItem.getQuantity());
		verify(bookRepository, times(1)).findById(anyLong());
		verify(cartRepository, times(1)).save(any());
	}
	@Test
    void testUpdateBookQuantity() {
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cartItem));
        doNothing().when(cartItemValidator).validateUserAuthorization(any(), anyLong());
        when(cartRepository.save(any())).thenReturn(cartItem);
        ShoppingCartItem updatedItem = shoppingCartService.updateBookQuantity(cartRequest);
        assertNotNull(updatedItem);
        assertEquals(3, updatedItem.getQuantity());
        verify(cartRepository, times(1)).findById(anyLong());
        verify(cartItemValidator, times(1)).validateUserAuthorization(any(), anyLong());
        verify(cartRepository, times(1)).save(any());
    }
	
	 @Test
	    void testRemoveBookFromCart() {
	        when(cartRepository.findByUserIdAndBookId(anyLong(), anyLong())).thenReturn(Optional.of(cartItem));
	        shoppingCartService.removeBookFromCart(1L, 1L);
	        verify(cartRepository, times(1)).delete(cartItem);
	    }
	 
}
