package br.com.univates.mvc.event.controller.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.univates.mvc.event.model.entity.User;
import br.com.univates.mvc.event.model.type.UserRole;

/**
 * @author Arthur
 */
public class RequestNewUserDTO {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;	
	private UserRole role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public User toUser() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role != null ? role : UserRole.PADRAO);
		user.setEnabled(true);

		return user;
	}

}
