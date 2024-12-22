package com.bnp.kata.onlinebookstore.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bnp.kata.onlinebookstore.dto.UserRegisterRequest;
import com.bnp.kata.onlinebookstore.exception.UserAlreadyExistsException;
import com.bnp.kata.onlinebookstore.model.User;
import com.bnp.kata.onlinebookstore.repository.UserRepository;

@Service
public class UserRegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User register(UserRegisterRequest userRegisterRequest) {
		userRepository.findByUserName(userRegisterRequest.getUserName()).ifPresent(user -> {
			throw new UserAlreadyExistsException("User Already exists");
		});

		User user = new User();
		user.setUserName(userRegisterRequest.getUserName());
		user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
		user.setEmail(Objects.nonNull(userRegisterRequest.getEmail()) ? userRegisterRequest.getEmail() : "");
		user.setFirstName(
				Objects.nonNull(userRegisterRequest.getFirstName()) ? userRegisterRequest.getFirstName() : "");

		return userRepository.save(user);

	}

	

}
