package com.bnp.kata.onlinebookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bnp.kata.onlinebookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
