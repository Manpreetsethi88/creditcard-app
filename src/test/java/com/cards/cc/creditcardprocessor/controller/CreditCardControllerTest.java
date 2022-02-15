package com.cards.cc.creditcardprocessor.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

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
		
		ResponseEntity<CreditCardGenericResponse> response = creditCardController.addCard(ccRequest);
		CreditCardGenericResponse ccGenericResponse = response.getBody();
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
        ResponseEntity<CreditCardGenericResponse> response = creditCardController.addCard(ccRequest);
        assertEquals(ccGenericResponse, response.getBody());
	}
	
	@Test
	  public void testAddCardPatternValidation() {
		CreditCardRequestParam creditCardRequestParam = new CreditCardRequestParam();
		creditCardRequestParam.setCardNumber("4111111111111111a");
		creditCardRequestParam.setFirstName("Manpreet");
		creditCardRequestParam.setLimit(new BigDecimal(4500.02));
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CreditCardRequestParam>> constraintViolations = validator.validate(creditCardRequestParam);
		assertEquals(constraintViolations.size(), 1);
		ConstraintViolation<CreditCardRequestParam> violation = constraintViolations.iterator().next();
		assertEquals("must contain only digits[0-9]", violation.getMessage());
		assertEquals("cardNumber", violation.getPropertyPath().toString());
	  }
	  
	  @Test
	  public void testAddCardLengthValidation() {
		CreditCardRequestParam creditCardRequestParam = new CreditCardRequestParam();
		creditCardRequestParam.setCardNumber("41111111111111114444");
		creditCardRequestParam.setFirstName("Manpreet");
		creditCardRequestParam.setLimit(new BigDecimal(10000.56));
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CreditCardRequestParam>> constraintViolations = validator.validate(creditCardRequestParam);
		assertEquals(constraintViolations.size(), 1);
		ConstraintViolation<CreditCardRequestParam> violation = constraintViolations.iterator().next();
		assertEquals("must be between 0 and 19 digits long", violation.getMessage());
		assertEquals("cardNumber", violation.getPropertyPath().toString());
	  }

}
