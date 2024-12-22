package com.bnp.kata.onlinebookstore.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bnp.kata.onlinebookstore.dto.UserRegisterRequest;
import com.bnp.kata.onlinebookstore.model.User;
import com.bnp.kata.onlinebookstore.services.UserRegistrationService;

public class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserRegistrationService userRegistrationService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void shouldRegisterUserSuccessfullyhenValidRequestIsSent() throws Exception {
		UserRegisterRequest registerRequest = new UserRegisterRequest("user", "password", "email@example.com", "First",
				"Last");

		User user = new User(1L, "user", "encodedPassword", "email@example.com", "First", "Last");
		when(userRegistrationService.register(registerRequest)).thenReturn(user);

		mockMvc.perform(
				post("/api/v1/user/register").contentType("application/json").content(convertToJson(registerRequest)))
				.andExpect(status().isCreated());
	}

	private String convertToJson(Object request) {
		if (request instanceof UserRegisterRequest) {
			UserRegisterRequest registerRequest = (UserRegisterRequest) request;
			return String.format(
					"{\"userName\":\"%s\", \"password\":\"%s\", \"email\":\"%s\", \"firstName\":\"%s\", \"lastName\":\"%s\"}",
					registerRequest.getUserName(), registerRequest.getPassword(), registerRequest.getEmail(),
					registerRequest.getFirstName(), registerRequest.getLastName());

		}
		return "{}";
	}
}
