package com.mindcanary.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice()
public class GlobalExceptionHandler {

	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ServiceError handleException(Exception exception) {
		
		String finalMessage = exception.getMessage();
		logger.error(finalMessage);
		exception.printStackTrace();
		return new ServiceError("error", finalMessage);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody ServiceError handleAuthorizationException(Exception exception) {

		return new ServiceError("error", exception.getMessage());
	}
	
}
