package com.bnp.kata.onlinebookstore.controller;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bnp.kata.onlinebookstore.dto.OrderRequest;
import com.bnp.kata.onlinebookstore.model.Order;
import com.bnp.kata.onlinebookstore.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        if (Objects.nonNull(orderRequest) && Objects.nonNull(orderRequest.getUserId())) {
            Order order = orderService.placeOrder(orderRequest.getUserId());
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
