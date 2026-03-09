package com.dyashin.coreconnectmessagingplatform.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.coreconnectmessagingplatform.dto.AuthLoginDTO;
import com.dyashin.coreconnectmessagingplatform.dto.AuthRegisterDTO;
import com.dyashin.coreconnectmessagingplatform.dto.UserResponseDTO;
import com.dyashin.coreconnectmessagingplatform.entity.User;
import com.dyashin.coreconnectmessagingplatform.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class UserAuthController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody AuthRegisterDTO authRegisterDTO) {
		boolean registered = userService.register(authRegisterDTO);
		Map<String, Object> response = new HashMap<>();
		int status = registered ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (registered) {
			response.put("error", false);
			response.put("message", "User registered successfully");
		} else {
			response.put("error", true);
			response.put("message", "User already exists");
		}
		return ResponseEntity.status(status).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthLoginDTO authLoginDTO) {
		String token = userService.login(authLoginDTO);
		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("token", token);
		return ResponseEntity.ok(response);
	}

}
