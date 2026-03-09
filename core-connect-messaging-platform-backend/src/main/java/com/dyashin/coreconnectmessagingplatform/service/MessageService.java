package com.dyashin.coreconnectmessagingplatform.service;

import java.util.List;

import com.dyashin.coreconnectmessagingplatform.dto.MessageDTO;
import com.dyashin.coreconnectmessagingplatform.dto.MessageResponseDTO;
import com.dyashin.coreconnectmessagingplatform.dto.UserResponseDTO;
import com.dyashin.coreconnectmessagingplatform.entity.Message;

public interface MessageService {

	List<MessageResponseDTO> getConversation(int otherUserId);
	
	Message sendMessage(MessageDTO messageDTO);
	
}
