package com.bnp.kata.onlinebookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@PostMapping("/createBook")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book savedBook = bookService.createBook(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

	
}
