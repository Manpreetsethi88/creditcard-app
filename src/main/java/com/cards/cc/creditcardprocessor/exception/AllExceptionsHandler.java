package com.cards.cc.creditcardprocessor.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;
import com.cards.cc.creditcardprocessor.response.CreditCardErrorResponse;

@ControllerAdvice
public class AllExceptionsHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<CreditCardErrorResponse> processUnmergeException(final MethodArgumentNotValidException ex) {
		List<String> errorsList = ex.getBindingResult().getFieldErrors().stream()
							.map(error -> error.getField() + " " +error.getDefaultMessage() + "[value :" + error.getRejectedValue() + "]")
							.collect(Collectors.toList());
	    CreditCardErrorResponse errors = new CreditCardErrorResponse();
	    errors.setMessage(errorsList.toString());
	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CreditCardErrorResponse> processUnmergeException(final DataAccessException ex) {
    	CreditCardErrorResponse errors = new CreditCardErrorResponse();
    	errors.setMessage(CreditCardDetailsConstants.INVALID_REQUEST);
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CreditCardErrorResponse> processUnmergeException(final DataIntegrityViolationException ex) {
    	CreditCardErrorResponse errors = new CreditCardErrorResponse();
    	errors.setMessage(CreditCardDetailsConstants.CARD_ALREADY_EXIST);
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CreditCardErrorResponse> processUnmergeException(final Exception ex) {
    	CreditCardErrorResponse errors = new CreditCardErrorResponse();
    	errors.setMessage(CreditCardDetailsConstants.GENERIC_ERROR_MESSAGE);
    	return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
