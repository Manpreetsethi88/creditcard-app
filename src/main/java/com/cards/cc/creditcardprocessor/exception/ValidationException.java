package com.cards.cc.creditcardprocessor.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cards.cc.creditcardprocessor.response.CreditCardErrorResponse;

@ControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler{
	
	@SuppressWarnings("rawtypes")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			StringBuffer errorString = new StringBuffer();
			if(error instanceof FieldError) {
				errorString.append(((FieldError) error).getField() + " ");
			}
			errorString.append(error.getDefaultMessage());
			details.add(errorString.toString());
	    }
	    CreditCardErrorResponse error = new CreditCardErrorResponse();
	    error.setMessage(details.toString());
	    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
