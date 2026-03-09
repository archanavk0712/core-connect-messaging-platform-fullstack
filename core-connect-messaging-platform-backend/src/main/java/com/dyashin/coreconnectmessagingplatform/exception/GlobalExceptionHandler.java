package com.dyashin.coreconnectmessagingplatform.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MessageException.class)
	public ResponseEntity<?> handleMessageException(MessageException e) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", true);
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	//Spring creates Binding result with the help of DTO classes hence it is already organized and stored
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> field = new HashMap<>();
		e.getBindingResult().getFieldErrors()
				.forEach(error -> field.put(error.getField(), error.getDefaultMessage()));
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("error", true);
		response.put("validationErrors", field);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
		Map<String, String> response = new HashMap<>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			
			String fullPath = violation.getPropertyPath().toString();

			String field = fullPath.contains(".") ? fullPath.substring(fullPath.lastIndexOf(".") + 1) : fullPath;

			response.put(field, violation.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		Map<String, String> response = new HashMap<>();
		response.put(e.getName(), "Invalid "+e.getName()+" Must be correct format");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e) {
		Map<String, String> response = new HashMap<>();
		response.put("error", "Invalid API endpoint");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleOtherExceptions(Exception e) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("error", true);
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
