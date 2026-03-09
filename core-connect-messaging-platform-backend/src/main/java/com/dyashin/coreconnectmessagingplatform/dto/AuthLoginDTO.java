package com.dyashin.coreconnectmessagingplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthLoginDTO {

	@NotBlank(message = "Password is required")
	@Size(min=2, max=10, message = "Password must be between 2 and 10 characters")
	private String password;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email format")
	private String email;
}
