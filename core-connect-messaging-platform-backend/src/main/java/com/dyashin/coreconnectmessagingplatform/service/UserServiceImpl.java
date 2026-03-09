package com.dyashin.coreconnectmessagingplatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dyashin.coreconnectmessagingplatform.dto.AuthLoginDTO;
import com.dyashin.coreconnectmessagingplatform.dto.AuthRegisterDTO;
import com.dyashin.coreconnectmessagingplatform.dto.UserResponseDTO;
import com.dyashin.coreconnectmessagingplatform.entity.User;
import com.dyashin.coreconnectmessagingplatform.exception.MessageException;
import com.dyashin.coreconnectmessagingplatform.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    UserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

	@Override
	public boolean register(AuthRegisterDTO authRegisterDTO) {
		Optional<User> existingUser = userRepo.findByEmail(authRegisterDTO.getEmail());
		if (existingUser.isPresent()) {
			return false;
		}

		User user = new User();
		user.setUserName(authRegisterDTO.getUserName());
		user.setEmail(authRegisterDTO.getEmail());
		user.setPassword(passwordEncoder.encode(authRegisterDTO.getPassword()));
		userRepo.save(user);
		return true;
	}

	@Override
	public String login(AuthLoginDTO authLoginDTO) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginDTO.getEmail(),authLoginDTO.getPassword()));
		} catch (Exception e) {
			throw new MessageException("Invalid credentials");
		}
		return jwtService.generateToken(authLoginDTO.getEmail());
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserResponseDTO> responseList = new ArrayList<>();

		for (User user : users) {
			UserResponseDTO userResponseDTO = new UserResponseDTO();
			userResponseDTO.setUserId(user.getUserId());
			userResponseDTO.setUserName(user.getUserName());
			userResponseDTO.setEmail(user.getEmail());
			responseList.add(userResponseDTO);
		}
		return responseList;
	}
}
