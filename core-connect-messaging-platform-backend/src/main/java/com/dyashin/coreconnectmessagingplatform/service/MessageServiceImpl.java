package com.dyashin.coreconnectmessagingplatform.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyashin.coreconnectmessagingplatform.dto.MessageDTO;
import com.dyashin.coreconnectmessagingplatform.dto.MessageResponseDTO;
import com.dyashin.coreconnectmessagingplatform.dto.UserResponseDTO;
import com.dyashin.coreconnectmessagingplatform.entity.Message;
import com.dyashin.coreconnectmessagingplatform.entity.User;
import com.dyashin.coreconnectmessagingplatform.exception.MessageException;
import com.dyashin.coreconnectmessagingplatform.repo.MessageRepo;
import com.dyashin.coreconnectmessagingplatform.repo.UserRepo;
import com.dyashin.coreconnectmessagingplatform.util.SecurityUtil;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepo messageRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public List<MessageResponseDTO> getConversation(int otherUserId) {
		String email = SecurityUtil.getLoggedUserEmail();

		User loggedInUser = userRepo.findByEmail(email).orElseThrow(() -> new MessageException("User not found"));

		User otherUser = userRepo.findById(otherUserId).orElseThrow(() -> new MessageException("User not found"));

		List<Message> messages= messageRepo.findConversation(loggedInUser, otherUser);
		List<MessageResponseDTO> responseList=new ArrayList<>();
		
		for(Message message: messages) {
			
			UserResponseDTO senderDTO=new UserResponseDTO();
			senderDTO.setUserId(message.getSender().getUserId());
			senderDTO.setUserName(message.getSender().getUserName());
			senderDTO.setEmail(message.getSender().getEmail());
			
			UserResponseDTO receiverDTO= new UserResponseDTO();
			receiverDTO.setUserId(message.getReceiver().getUserId());
			receiverDTO.setUserName(message.getReceiver().getUserName());
			receiverDTO.setEmail(message.getReceiver().getEmail());
			
			MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
			messageResponseDTO.setMessageId(message.getMessageId());
			messageResponseDTO.setContent(message.getContent());
			messageResponseDTO.setTimestamp(message.getTimestamp());
			messageResponseDTO.setSender(senderDTO);
			messageResponseDTO.setReceiver(receiverDTO);
			
			responseList.add(messageResponseDTO);
			
		}
		return responseList;
	}

	@Override
	public Message sendMessage(MessageDTO messageDTO) {

		String email = SecurityUtil.getLoggedUserEmail();

		User sender = userRepo.findByEmail(email).orElseThrow(() -> new MessageException("Sender not found"));

		User receiver = userRepo.findById(messageDTO.getReceiverId())
				.orElseThrow(() -> new MessageException("Receiver not found"));

		if(sender.getUserId() == receiver.getUserId()) {
			throw new MessageException("You cannot send message to yourself");
		}
		Message message = new Message();
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setContent(messageDTO.getContent());
		message.setTimestamp(LocalDateTime.now());

		return messageRepo.save(message);

	}

}
