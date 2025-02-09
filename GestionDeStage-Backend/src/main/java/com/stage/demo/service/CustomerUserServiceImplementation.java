package com.stage.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stage.demo.entities.Stagiaire;
import com.stage.demo.entities.User;
import com.stage.demo.repository.UserRepository;

@Service
public class CustomerUserServiceImplementation implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Stagiaire user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found with email" + username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getMotDePasse(), authorities);
	}

}
