package com.SpringsecurityJwtToken.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.SpringsecurityJwtToken.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.SpringsecurityJwtToken.model.User;

@Service
public class UserserviceImpl implements Userservice, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Integer saveUser(User user) {
		bCryptPasswordEncoder.encode(user.getPassword());
		return userRepository.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = findByUsername(username);
		User user = opt.get();
		if (user == null) {
			throw new UsernameNotFoundException("User Not exist");
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}

}
