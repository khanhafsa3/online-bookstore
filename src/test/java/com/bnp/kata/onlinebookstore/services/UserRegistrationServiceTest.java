package com.bnp.kata.onlinebookstore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.bnp.kata.onlinebookstore.dto.LoginRequest;
import com.bnp.kata.onlinebookstore.dto.UserRegisterRequest;
import com.bnp.kata.onlinebookstore.exception.UserAlreadyExistsException;
import com.bnp.kata.onlinebookstore.model.User;
import com.bnp.kata.onlinebookstore.repository.UserRepository;
public class UserRegistrationServiceTest {

	@InjectMocks
	private UserRegistrationService userRegistrationService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegisterSuccess() {
		UserRegisterRequest request = new UserRegisterRequest("StuartBroad", "password123", "test@example.com", "AK",
				"Stuart");

		when(userRepository.findByUserName(request.getUserName())).thenReturn(Optional.empty());
		when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		User registeredUser = userRegistrationService.register(request);

		assertNotNull(registeredUser);
		assertEquals(request.getUserName(), registeredUser.getUserName());
		assertEquals("encodedPassword", registeredUser.getPassword());
		verify(userRepository).save(any(User.class));
	}

	@Test
	public void testRegisterUserAlreadyExists() {
		UserRegisterRequest request = new UserRegisterRequest("Johny", "password123", "test@example.com", "John",
				"Abraham");
		User existingUser = new User();
		existingUser.setUserName("Johny");

		when(userRepository.findByUserName(request.getUserName())).thenReturn(Optional.of(existingUser));

		assertThrows(UserAlreadyExistsException.class, () -> userRegistrationService.register(request));
		verify(userRepository, never()).save(any(User.class));
	}

	@Test
	public void testIsUserFoundUserExistsAndPasswordMatches() {
		LoginRequest request = new LoginRequest("testUser", "password123");
		User user = new User();
		user.setUserName("testUser");
		user.setPassword("encodedPassword");

		when(userRepository.findByUserName(request.getUserName())).thenReturn(Optional.of(user));
		when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);

		assertTrue(userRegistrationService.isUserFound(request));
	}

	@Test
	public void testIsUserFoundUserDoesNotExist() {
		LoginRequest request = new LoginRequest("Drake", "password123");

		when(userRepository.findByUserName(request.getUserName())).thenReturn(Optional.empty());

		assertFalse(userRegistrationService.isUserFound(request));
	}

	@Test
	public void testIsUserFoundUserExists_ButPasswordDoesNotMatch() {
		LoginRequest request = new LoginRequest("Ak", "wrongPassword");
		User user = new User();
		user.setUserName("Ak");
		user.setPassword("correctEncodedPassword");

		when(userRepository.findByUserName(request.getUserName())).thenReturn(Optional.of(user));
		when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);

		assertFalse(userRegistrationService.isUserFound(request));
	}
}
