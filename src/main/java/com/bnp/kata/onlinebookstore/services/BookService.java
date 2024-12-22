package com.bnp.kata.onlinebookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
