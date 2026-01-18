/**
 * 
 */
package com.ansh.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ansh.security.DTO.CustomUserDetails;
import com.ansh.security.DTO.User;
import com.ansh.security.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author am619
 *
 */

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		if (user == null) {
			log.info("User [" + username + "] not found.");
			throw new UsernameNotFoundException("User [" + username + "] not found.");
		}
		return new CustomUserDetails(user);
	}

}
