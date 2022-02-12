package com.cards.cc.creditcardprocessor.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;
import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.service.ICreditCardService;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardControllerTest {
	
	@Mock
	ICreditCardService iCreditCardService;
	
	@InjectMocks
	CreditCardController creditCardController;
	
	@Test
	public void testAddCardFailure() {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4012888888881871");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		
		CreditCardGenericResponse ccGenericResponse = creditCardController.addCard(ccRequest);
		assertEquals(CreditCardDetailsConstants.CARD_NUMBER_ERROR, ccGenericResponse.getErrors().getMessage());
	}
	
	@Test
	public void testAddCardSuccess() {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		
		CreditCardGenericResponse ccGenericResponse = mock(CreditCardGenericResponse.class);
        when(iCreditCardService.saveCreditCardDetails(Mockito.any())).thenReturn(ccGenericResponse);
        CreditCardGenericResponse response = creditCardController.addCard(ccRequest);
        assertEquals(ccGenericResponse, response);
	}

}
