package com.dyashin.coreconnectmessagingplatform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.coreconnectmessagingplatform.dto.UserResponseDTO;
import com.dyashin.coreconnectmessagingplatform.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<?> viewAllUsers() {
		List<UserResponseDTO> list = userService.getAllUsers();
		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("Users", list);
		return ResponseEntity.ok(response);
	}

}
