package com.jbk.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap=new HashMap<>();
		List<FieldError> fieldErrors = ex.getFieldErrors();
		
		for (FieldError fieldError : fieldErrors) {
			
			String field = fieldError.getField();
			String defaultMessage = fieldError.getDefaultMessage();
			
			errorMap.put(field, defaultMessage);
			
		}
		return errorMap;
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public CustomizeExceptionResponse resourceAlreadyExistException(ResourceAlreadyExistException ex) {
		
		CustomizeExceptionResponse response=new CustomizeExceptionResponse();
		response.setDefaultMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setDate(new Date());
		return response;
		
	}
	
	@ExceptionHandler(ResourceNotExist.class)
	@ResponseStatus(code = HttpStatus.OK)
	public CustomizeExceptionResponse resourceNotExist(ResourceNotExist ex) {
		
		CustomizeExceptionResponse response=new CustomizeExceptionResponse();
		response.setDefaultMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.NO_CONTENT.value());
		response.setDate(new Date());
		return response;
		
	}
	
	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public CustomizeExceptionResponse somethingWentWrongException(SomethingWentWrongException ex) {
		
		CustomizeExceptionResponse response=new CustomizeExceptionResponse();
		response.setDefaultMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setDate(new Date());
		return response;
		
	}
	
	


}
