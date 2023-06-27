package com.SpringsecurityJwtToken.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringsecurityJwtToken.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	Optional<User> findByUsername(String username);
}
