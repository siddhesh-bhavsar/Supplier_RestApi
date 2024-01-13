package com.jbk.Exception;

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
	public com.jbk.Model.CustomizeExceptionResponse resourceAlreadyExistException(ResourceAlreadyExistException ex) {
		
		com.jbk.Model.CustomizeExceptionResponse response=new com.jbk.Model.CustomizeExceptionResponse();
		response.setDefaultMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.CONFLICT.value());
		response.setDate(new Date());
		return response;
		
	}
	
	@ExceptionHandler(ResourceNotExist.class)
	@ResponseStatus(code = HttpStatus.OK)
	public com.jbk.Model.CustomizeExceptionResponse ResourceNotExist(ResourceNotExist ex) {
		
		com.jbk.Model.CustomizeExceptionResponse response=new com.jbk.Model.CustomizeExceptionResponse();
		response.setDefaultMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.NO_CONTENT .value());
		response.setDate(new Date());
		return response;
		
	}
	@ExceptionHandler(SomethingWentWrong.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public com.jbk.Model.CustomizeExceptionResponse SomethingWentWrong(SomethingWentWrong ex) {
		
		com.jbk.Model.CustomizeExceptionResponse response=new com.jbk.Model.CustomizeExceptionResponse();
		response.setDefaultMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR .value());
		response.setDate(new Date());
		return response;
		
	}

}
