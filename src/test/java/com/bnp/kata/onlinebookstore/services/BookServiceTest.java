package com.bnp.kata.onlinebookstore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	private Book book1;
	private Book book2;

	@BeforeEach
	void setUp() {
		book1 = new Book();
		book1.setId(1L);
		book1.setTitle("Book One");
		book1.setPrice(19.99);

		book2 = new Book();
		book2.setId(2L);
		book2.setTitle("Book Two");
		book2.setPrice(29.99);
	}

	@Test
	void testCreateBook() {
		Book newBook = new Book();
		newBook.setTitle("Book Three");
		newBook.setPrice(39.99);
		when(bookRepository.save(newBook)).thenReturn(book1);
		Book createdBook = bookService.createBook(newBook);
		assertNotNull(createdBook);
		assertEquals("Book One", createdBook.getTitle());
		verify(bookRepository, times(1)).save(newBook);
	}

	@Test
	void testGetAllBooks() {
		when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
		List<Book> books = bookService.getAllBooks();
		assertNotNull(books);
		assertEquals(2, books.size());
		assertEquals("Book One", books.get(0).getTitle());
		assertEquals("Book Two", books.get(1).getTitle());
		verify(bookRepository, times(1)).findAll();
	}

}
