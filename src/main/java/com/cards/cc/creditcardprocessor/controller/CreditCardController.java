package com.cards.cc.creditcardprocessor.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;
import com.cards.cc.creditcardprocessor.model.CreditCardDetails;
import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.cards.cc.creditcardprocessor.response.CreditCardErrorResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;
import com.cards.cc.creditcardprocessor.service.ICreditCardService;
import com.cards.cc.creditcardprocessor.util.CreditCardValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(value = "CreditCardController", description = "REST APIs related to credit card operations")
@RestController
public class CreditCardController {
	
	@Autowired
	private ICreditCardService creditCardService;
	
	@RequestMapping(value = "/addCard",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
    @ApiOperation("Add a new credit card")
	@ApiResponses(value = {
            @ApiResponse(message = "OK", code = 200, response = CreditCardGenericResponse.class),
            @ApiResponse(message = "Bad Data Input", code = 400, response = CreditCardErrorResponse.class),
            @ApiResponse(message = "Internal Server Error", code = 500, response = CreditCardErrorResponse.class)})
    public ResponseEntity<CreditCardGenericResponse> addCard( @Valid @RequestBody CreditCardRequestParam request) {
		CreditCardGenericResponse ccGenericResponse = new CreditCardGenericResponse();
		CreditCardDetails creditCardDetails = new CreditCardDetails(request);
		if (CreditCardValidator.luhnCheck(request.getCardNumber())) {
			try {
				ccGenericResponse = creditCardService.saveCreditCardDetails(creditCardDetails);
			} catch (DuplicateKeyException e) {
				CreditCardErrorResponse errors = new CreditCardErrorResponse();
				errors.setMessage(CreditCardDetailsConstants.CARD_ALREADY_EXIST);
				ccGenericResponse.setErrors(errors);
				return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (DataAccessException e) {
				CreditCardErrorResponse errors = new CreditCardErrorResponse();
				errors.setMessage(CreditCardDetailsConstants.GENERIC_ERROR_MESSAGE);
				ccGenericResponse.setErrors(errors);
				return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
	            ccGenericResponse.setCardNumber(request.getCardNumber());
	            CreditCardErrorResponse errors = new CreditCardErrorResponse();
	            errors.setMessage(CreditCardDetailsConstants.CARD_NUMBER_ERROR);
	            ccGenericResponse.setErrors(errors);
	            return new ResponseEntity(ccGenericResponse, HttpStatus.BAD_REQUEST);
	        }
		return new ResponseEntity(ccGenericResponse, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getAllCards",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
    @ApiOperation("List all the credit cards from the system")
	@ApiResponses(value = {
            @ApiResponse(message = "OK", code = 200, response = CreditCardGenericResponse.class),
            @ApiResponse(message = "Internal Server Error", code = 500, response = CreditCardErrorResponse.class)})
	public ResponseEntity<CreditCardListResponse> viewAllCreditCards() {
		CreditCardListResponse ccListResponse = new CreditCardListResponse();
		try {
			ccListResponse = creditCardService.getAllCreditCardDetails();
			} catch (DataAccessException e) {
			CreditCardErrorResponse errors = new CreditCardErrorResponse();
			errors.setMessage(CreditCardDetailsConstants.GENERIC_ERROR_MESSAGE);
			ccListResponse.setErrors(errors);
			return new ResponseEntity(ccListResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity(ccListResponse, HttpStatus.OK);
	}
}
