package com.bnp.kata.onlinebookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
	Optional<ShoppingCartItem> findByUserIdAndBookId(Long userId, Long bookId);
}
