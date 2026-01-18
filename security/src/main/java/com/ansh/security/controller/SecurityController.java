/**
 * 
 */
package com.ansh.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author am619
 *
 */

@RestController
public class SecurityController {

	@GetMapping("/home")
	public ResponseEntity<?> greet(HttpServletRequest request) {
		return new ResponseEntity<String>("Welcome to Spring Security Project.", HttpStatus.OK);
	}

}
