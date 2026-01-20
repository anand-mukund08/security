/**
 * 
 */
package com.ansh.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ansh.security.DTO.User;
import com.ansh.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author am619
 *
 */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		String message = "User [" + user.getUserName() + "] register successfully";
		try {
			service.registerUser(user);
		} catch (Exception e) {
			message = "Error while regestering user [" + user.getUserName() + "]. " + e.getLocalizedMessage();
			log.error("Error while regestering user [" + user.getUserName() + "]", e.getLocalizedMessage(), e);
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		String message = "User [" + user.getUserName() + "] logged in successfully";
		try {
			message=service.verifyUser(user);
		} catch (Exception e) {
			message = "Error while logged in user [" + user.getUserName() + "]. " + e.getLocalizedMessage();
			log.error("Error while logged in user [" + user.getUserName() + "]", e.getLocalizedMessage(), e);
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);

	}
}

