package com.SpringsecurityJwtToken.service;

import java.util.Optional;

import com.SpringsecurityJwtToken.model.User;

public interface Userservice {
	
	Integer saveUser(User user);
	Optional<User> findByUsername(String username);
	

}
