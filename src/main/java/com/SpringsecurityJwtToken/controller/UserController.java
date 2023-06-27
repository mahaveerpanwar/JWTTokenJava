package com.SpringsecurityJwtToken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringsecurityJwtToken.model.User;
import com.SpringsecurityJwtToken.model.UserJwtRequest;
import com.SpringsecurityJwtToken.model.UserJwtResponse;
import com.SpringsecurityJwtToken.service.Userservice;
import com.SpringsecurityJwtToken.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Userservice userservice;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {

		Integer id = userservice.saveUser(user);
		String body = "User" + " " + id + " " + "Saved";
		return ResponseEntity.ok(body);
	}

	@PostMapping("/login")
	public ResponseEntity<UserJwtResponse> validateandgeneratetoken(@RequestBody UserJwtRequest userJwtRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userJwtRequest.getUsername(), userJwtRequest.getPassword()));
		String token = jwtUtil.generatetoken(userJwtRequest.getUsername());
		return ResponseEntity.ok(new UserJwtResponse(token, "Success"));

	}

	@PostMapping("/welcome")
	public ResponseEntity<String> accessData(Principal p) {
		return ResponseEntity.ok("Hello");
	}
}
