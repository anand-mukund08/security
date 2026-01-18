/**
 * 
 */
package com.ansh.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ansh.security.DTO.User;

/**
 * @author am619
 *
 */

public interface UserRepo extends JpaRepository<User, String> {

	/**
	 * @param username
	 * @return
	 */
	User findByUserName(String username);

}
