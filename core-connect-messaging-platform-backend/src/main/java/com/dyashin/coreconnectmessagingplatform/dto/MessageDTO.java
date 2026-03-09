package com.dyashin.coreconnectmessagingplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageDTO {

	@NotNull(message = "Receiver required")
	private int receiverId;

	@NotBlank(message = "Message cannot be empty")
	@Size(max = 2000, message = "Message too long")
	private String content;

}
