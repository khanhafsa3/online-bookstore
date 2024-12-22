
package com.bnp.kata.onlinebookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bnp.kata.onlinebookstore.dto.UserRegisterRequest;
import com.bnp.kata.onlinebookstore.model.User;
import com.bnp.kata.onlinebookstore.services.UserRegistrationService;

@RestController

@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody UserRegisterRequest userRegisterRequest) {
		User user = userRegistrationService.register(userRegisterRequest);
		return new ResponseEntity<>(user, HttpStatus.CREATED);

	}

}
