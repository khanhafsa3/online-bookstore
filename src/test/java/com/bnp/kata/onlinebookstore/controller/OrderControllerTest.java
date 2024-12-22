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
import com.bnp.kata.onlinebookstore.dto.OrderRequest;
import com.bnp.kata.onlinebookstore.model.Order;
import com.bnp.kata.onlinebookstore.services.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Mock
	private OrderService orderService;

	@InjectMocks
	private OrderController orderController;

	private MockMvc mockMvc;

	private Order order;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		order = new Order();
		order.setId(1L);
		order.setUserId(1L);
		order.setTotalPrice(100.0);
	}

	@Test
	void shouldPlaceOrder_whenValidRequestIsSent() throws Exception {
		OrderRequest orderRequest = new OrderRequest(1L);
		when(orderService.placeOrder(1L)).thenReturn(order);

		mockMvc.perform(
				post("/api/v1/orders/placeOrder").contentType("application/json").content(convertToJson(orderRequest)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.userId").value(1L)).andExpect(jsonPath("$.totalPrice").value(100.0));

		verify(orderService, times(1)).placeOrder(1L);
	}

	@Test
	void shouldReturnBadRequest_whenEmptyRequestIsSent() throws Exception {
		mockMvc.perform(post("/api/v1/orders/placeOrder").contentType("application/json").content("{}"))
				.andExpect(status().isBadRequest());
	}

	private String convertToJson(OrderRequest orderRequest) {
		return String.format("{\"userId\": %d}", orderRequest.getUserId());
	}
}