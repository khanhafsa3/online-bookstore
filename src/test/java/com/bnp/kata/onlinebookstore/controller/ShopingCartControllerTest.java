package com.bnp.kata.onlinebookstore.controller;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bnp.kata.onlinebookstore.dto.ShoppingCartRequest;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.services.ShoppingCartService;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
public class ShopingCartControllerTest {
	@Mock
	private ShoppingCartService shoppingCartService;

	@InjectMocks
	private ShoppingCartController shoppingCartController;

	private MockMvc mockMvc;

	private ShoppingCartItem cartItem;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();

		cartItem = new ShoppingCartItem(1L, null, 1L, 2);
	}

	@Test
	void shouldAddBookToCartWhenValidRequestIsSent() throws Exception {
		ShoppingCartRequest cartRequest = new ShoppingCartRequest(1L, 1L, 1L, 2);
		when(shoppingCartService.addBookToCart(1L, 1L, 2)).thenReturn(cartItem);

		mockMvc.perform(post("/api/v1/cart/add").contentType("application/json").content(convertToJson(cartRequest)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.quantity").value(2));

		verify(shoppingCartService, times(1)).addBookToCart(1L, 1L, 2);
	}

	private String convertToJson(ShoppingCartRequest cartRequest) {
		return String.format("{\"bookId\": %d, \"userId\": %d, \"quantity\": %d}", cartRequest.getBookId(),
				cartRequest.getUserId(), cartRequest.getQuantity());
	}

}