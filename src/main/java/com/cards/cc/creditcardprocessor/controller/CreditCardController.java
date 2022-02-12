package com.cards.cc.creditcardprocessor.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import io.swagger.annotations.ApiOperation;

@RestController
public class CreditCardController {
	
	@Autowired
	private ICreditCardService creditCardService;
	
	@RequestMapping(value = "/addCard",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
    @ApiOperation("Add a new credit card")
    public CreditCardGenericResponse addCard( @Valid @RequestBody CreditCardRequestParam request) {

        CreditCardDetails creditCardDetails = new CreditCardDetails(request);
        CreditCardGenericResponse ccGenericResponse = new CreditCardGenericResponse();
        if (CreditCardValidator.luhnCheck(request.getCardNumber())) {
            ccGenericResponse = creditCardService.saveCreditCardDetails(creditCardDetails);
        } else {
            ccGenericResponse.setCardNumber(request.getCardNumber());
            CreditCardErrorResponse errors = new CreditCardErrorResponse();
            errors.setMessage(CreditCardDetailsConstants.CARD_NUMBER_ERROR);
            ccGenericResponse.setErrors(errors);
        }

        return ccGenericResponse;
    }
	
	@RequestMapping(value = "/getAllCards",
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET)
    @ApiOperation("List all the credit cards from the system")
	public CreditCardListResponse viewAllCreditCards() {
		return creditCardService.getAllCreditCardDetails();      
	}
}
