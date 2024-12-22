package com.bnp.kata.onlinebookstore.repository;

import com.bnp.kata.onlinebookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
