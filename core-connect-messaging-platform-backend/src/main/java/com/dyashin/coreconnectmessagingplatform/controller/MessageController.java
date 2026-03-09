package com.dyashin.coreconnectmessagingplatform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.coreconnectmessagingplatform.dto.MessageDTO;
import com.dyashin.coreconnectmessagingplatform.dto.MessageResponseDTO;
import com.dyashin.coreconnectmessagingplatform.entity.Message;
import com.dyashin.coreconnectmessagingplatform.exception.MessageException;
import com.dyashin.coreconnectmessagingplatform.service.MessageService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
@Validated
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/{contactId}")
	public ResponseEntity<?> findConversation(@PathVariable @Min(value = 1, message = "Invalid userId") Integer contactId) {
		List<MessageResponseDTO> list = messageService.getConversation(contactId);
		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("data", list);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/send")
	public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageDTO messageDTO){
			Message sent=messageService.sendMessage(messageDTO);
			Map<String, Object> response=new HashMap<>();
			int status=(sent!=null)?HttpStatus.OK.value():HttpStatus.BAD_REQUEST.value();
			response.put("status", status);
			if(sent!=null) {
				response.put("error", false);
				response.put("message", "Message sent successfully");
			}else {
				response.put("error", true);
				response.put("message", "Unable to send message");
			}
			return ResponseEntity.status(status).body(response);
			
	}
}