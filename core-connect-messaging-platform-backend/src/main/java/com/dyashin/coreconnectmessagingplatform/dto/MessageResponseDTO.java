package com.dyashin.coreconnectmessagingplatform.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageResponseDTO {

	private int messageId;
	
	private UserResponseDTO sender;
	
	private UserResponseDTO receiver;
	
	private String content;
	
	private LocalDateTime timestamp;
}
