package com.bnp.kata.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnp.kata.onlinebookstore.model.ShoppingCartItem;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {

}
