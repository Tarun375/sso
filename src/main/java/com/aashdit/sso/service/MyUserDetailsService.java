package com.aashdit.sso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aashdit.sso.entity.User;
import com.aashdit.sso.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> lUser = userRepository.findByUserName(username);
		if (lUser.isEmpty())
			throw new UsernameNotFoundException("User not found");

		return new UserDetailsImpl(lUser.get());
	}

}
