
package com.bnp.kata.onlinebookstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bnp.kata.onlinebookstore.dto.LoginRequest;
import com.bnp.kata.onlinebookstore.dto.UserRegisterRequest;
import com.bnp.kata.onlinebookstore.model.User;
import com.bnp.kata.onlinebookstore.services.UserRegistrationService;

@RestController

@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody UserRegisterRequest userRegisterRequest) {
		try {
			User user = userRegistrationService.register(userRegisterRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(
					Map.of("status", "Sucess", "message", "User created", "username", userRegisterRequest.getUserName()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("status", "fail", "message", e.getMessage()));
		}

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		boolean isUserFound = userRegistrationService.isUserFound(loginRequest);
		if (!isUserFound) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
		return ResponseEntity.ok("Logged in successfully");
	}

}
