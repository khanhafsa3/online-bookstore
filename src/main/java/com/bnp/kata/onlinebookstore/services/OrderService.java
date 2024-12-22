package com.bnp.kata.onlinebookstore.services;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bnp.kata.onlinebookstore.exception.ItemNotFoundException;
import com.bnp.kata.onlinebookstore.model.Order;
import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;
import com.bnp.kata.onlinebookstore.repository.OrderRepository;
import com.bnp.kata.onlinebookstore.repository.ShoppingCartRepository;
import com.bnp.kata.onlinebookstore.services.impl.PriceCalculator;

@Service
public class OrderService {

    private final ShoppingCartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final PriceCalculator priceCalculator;

    @Autowired
    public OrderService(ShoppingCartRepository cartRepository,
                        OrderRepository orderRepository,
                        PriceCalculator priceCalculator) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.priceCalculator = priceCalculator;
    }

    public Order placeOrder(Long userId) {
        List<ShoppingCartItem> cartItems = cartRepository.findByUserId(userId);

        validateCart(cartItems);

        double totalPrice = priceCalculator.calculateTotalPrice(cartItems);
        Order order = createOrder(userId, totalPrice);
        Order savedOrder = orderRepository.save(order);

        clearCart(cartItems);
        return savedOrder;
    }

    private void validateCart(List<ShoppingCartItem> cartItems) {
        if (cartItems.isEmpty()) {
            throw new ItemNotFoundException("Cart is empty. Cannot place an order.");
        }
    }

    private Order createOrder(Long userId, double totalPrice) {
        return new Order(userId, userId, totalPrice, LocalDateTime.now());
    }

    private void clearCart(List<ShoppingCartItem> cartItems) {
        cartRepository.deleteAll(cartItems);
    }
}