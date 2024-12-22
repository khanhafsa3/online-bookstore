package com.bnp.kata.onlinebookstore.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bnp.kata.onlinebookstore.model.Book;
import com.bnp.kata.onlinebookstore.services.BookService;

public class BookControllerTest {

	private MockMvc mockMvc;

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	void shouldCreateBookWhenCreateBookIsCalled() throws Exception {
		Book book = new Book(1L, "New Book", "Author One", 20.0);
		when(bookService.createBook(book)).thenReturn(book);

		mockMvc.perform(post("/api/v1/books/createBook").contentType("application/json").content(convertToJson(book)))
				.andExpect(status().isCreated());

	}

	@Test
	void shouldReturnAllBooksWhenGetAllBooksIsCalled() throws Exception {
		List<Book> books = createSampleBooks();
		when(bookService.getAllBooks()).thenReturn(books);

		mockMvc.perform(get("/api/v1/books/getAllBooks")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2)).andExpect(jsonPath("$[0].title").value("Book One"))
				.andExpect(jsonPath("$[1].title").value("Book Two"));
	}

	private List<Book> createSampleBooks() {
		Book book1 = new Book(1L, "Book One", "Author One", 10.0);
		Book book2 = new Book(2L, "Book Two", "Author Two", 15.0);
		return Arrays.asList(book1, book2);
	}

	private String convertToJson(Book book) {
		return String.format("{\"id\":%d,\"title\":\"%s\",\"author\":\"%s\",\"price\":%.2f}", book.getId(),

				book.getTitle(), book.getAuthor(), book.getPrice());
	}

}