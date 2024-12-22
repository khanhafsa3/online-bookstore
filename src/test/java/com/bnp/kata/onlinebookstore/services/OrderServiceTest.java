package com.bnp.kata.onlinebookstore.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.model.Order;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.repository.OrderRepository;
import com.bnp.kata.onlinebookstore.repository.ShoppingCartRepository;
import com.bnp.kata.onlinebookstore.services.impl.PriceCalculator;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ShoppingCartRepository cartRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PriceCalculator priceCalculator;

    @InjectMocks
    private OrderService orderService;

    private ShoppingCartItem cartItem1;
    private ShoppingCartItem cartItem2;

    @BeforeEach
    void setUp() {
        cartItem1 = new ShoppingCartItem();
        cartItem1.setQuantity(2);
        cartItem1.setBook(new Book(1L, "Book 1", "Williams", 20.0));
        cartItem2 = new ShoppingCartItem();
        cartItem2.setQuantity(1);
        cartItem2.setBook(new Book(2L, "Book 2", "Billiams", 20.0));
    }

    @Test
    void testPlaceOrder_Success() {
        Long userId = 1L;
        when(cartRepository.findByUserId(userId)).thenReturn(Arrays.asList(cartItem1, cartItem2));
        when(priceCalculator.calculateTotalPrice(anyList())).thenReturn(70.0);
        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setTotalPrice(70.0);
        order.setOrderDate(LocalDateTime.now());
        when(orderRepository.save(Mockito.any(Order.class)))
            .thenReturn(order);
      order = orderService.placeOrder(userId);
        assertEquals(userId, order.getUserId());
        assertEquals(70.0, order.getTotalPrice());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartRepository, times(1)).deleteAll(anyList());
    }

    @Test
    void testPlaceOrderCartEmpty() {
        Long userId = 1L;
        when(cartRepository.findByUserId(userId)).thenReturn(Collections.emptyList());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(userId));
        assertEquals("Cart is empty. Cannot place an order.", exception.getMessage());
    }
}
