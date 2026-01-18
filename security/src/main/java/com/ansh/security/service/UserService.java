/**
 * 
 */
package com.ansh.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ansh.security.DTO.User;
import com.ansh.security.repo.UserRepo;

/**
 * @author am619
 *
 */

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User registerUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

}
