package com.dyashin.coreconnectmessagingplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRegisterDTO {

	@NotBlank(message = "Username is required")
	@Size(min=2, max=30, message = "Username must be between 2 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$" , message = "Username can contain only letters, numbers and underscore")
	private String userName;

	@NotBlank(message = "Password is required")
	@Size(min=2, max=10, message = "Password must be between 2 and 10 characters")
	private String password;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
}
