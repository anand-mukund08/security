/**
 * 
 */
package com.ansh.security.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author am619
 *
 */
@Entity()
@Table(name = "users_details")
@Data
@JsonIgnoreProperties
public class User {
	@Id
	@Column(name = "username")
	@JsonProperty("username")
	private String userName;

	private String password;

}
