package com.dyashin.coreconnectmessagingplatform.service;

import java.util.List;

import com.dyashin.coreconnectmessagingplatform.dto.AuthLoginDTO;
import com.dyashin.coreconnectmessagingplatform.dto.AuthRegisterDTO;
import com.dyashin.coreconnectmessagingplatform.dto.UserResponseDTO;
import com.dyashin.coreconnectmessagingplatform.entity.User;

public interface UserService {

	boolean register(AuthRegisterDTO authRegisterDTO);

	String login(AuthLoginDTO authLoginDTO);

	List<UserResponseDTO> getAllUsers();

}
