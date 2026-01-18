/**
 * 
 */
package com.ansh.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ansh.security.DTO.CustomUserDetails;
import com.ansh.security.service.CustomUserDetailsService;
import com.ansh.security.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author am619
 *
 */
@Service
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private JWTService service;

	@Autowired
	ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		if (auth != null && auth.startsWith("Bearer")) {
			token = auth.substring(7);
			userName = service.extractUsername(token);
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails details = context.getBean(CustomUserDetailsService.class).loadUserByUsername(userName);
			if (service.validateToken(token, details)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(details, null,
						details.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
