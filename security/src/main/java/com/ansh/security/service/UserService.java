/**
 * 
 */
package com.ansh.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTService jwtService;

	public User registerUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	public String verifyUser(User user) {
		Authentication auth = manager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		if (auth.isAuthenticated())
			return jwtService.generateToken(user.getUserName());
		return "Failed";
	}

}
