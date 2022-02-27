package com.cards.cc.creditcardprocessor.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;
import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;
import com.cards.cc.creditcardprocessor.service.ICreditCardService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CreditCardController.class)
public class CreditCardControllerTest {
	
	@Autowired
    private MockMvc mvc;

	@MockBean
	ICreditCardService iCreditCardService;

	@Test
	public void testAddCardSuccess() throws Exception {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(2500.0));
		iCreditCardService.saveCreditCardDetails(Mockito.any(CreditCardRequestParam.class));
		
		mvc.perform(MockMvcRequestBuilders.post("/addCard")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .content(new Gson().toJson(ccRequest).toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void testAddCardFailure_InvalidCard() throws Exception {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4012888888881871");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(250.0));
		iCreditCardService.saveCreditCardDetails(Mockito.any(CreditCardRequestParam.class));
		
		mvc.perform(MockMvcRequestBuilders.post("/addCard")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .content(new Gson().toJson(ccRequest).toString()))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

	}
	
	 @Test
	 public void testAddCardFailure_DuplicateCard()  throws Exception {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(50.0));
	    Mockito.doThrow(new DataIntegrityViolationException("")).when(iCreditCardService).saveCreditCardDetails(Mockito.any(CreditCardRequestParam.class));

	    mvc.perform(MockMvcRequestBuilders.post("/addCard")
	    		.accept(MediaType.APPLICATION_JSON)
	    		.contentType(MediaType.APPLICATION_JSON)
	    		.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
	    		.content(new Gson().toJson(ccRequest).toString()))
	    .andExpect(MockMvcResultMatchers.status().isConflict());
	 }

	@SuppressWarnings("serial")
	@Test
	 public void testAddCardFailure_DatabaseException()  throws Exception {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(500.0));
		Mockito.doThrow(new DataAccessException(""){}).when(iCreditCardService).saveCreditCardDetails(Mockito.any(CreditCardRequestParam.class));
		 
		mvc.perform(MockMvcRequestBuilders.post("/addCard")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
				.content(new Gson().toJson(ccRequest).toString()))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	 }
	 
	 @SuppressWarnings("serial")
	@Test
	 public void testAddCardFailure_RuntimeException()  throws Exception {
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(500.0));
		Mockito.doThrow(new RuntimeException(""){}).when(iCreditCardService).saveCreditCardDetails(Mockito.any(CreditCardRequestParam.class));
			 
		mvc.perform(MockMvcRequestBuilders.post("/addCard")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
				.content(new Gson().toJson(ccRequest).toString()))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError());
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
		assertEquals(constraintViolations.size(), 2);
		List<String> violationMessages =  constraintViolations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
		List<String> violationProperties =  constraintViolations.stream().map(violation -> violation.getPropertyPath().toString()).collect(Collectors.toList());
		assertTrue("must be between 0 and 19 digits long",violationMessages.contains("must be between 0 and 19 digits long"));
		assertTrue("Credit card Number is invalid.", violationMessages.contains(CreditCardDetailsConstants.CARD_NUMBER_ERROR));
		assertTrue(violationProperties.contains("cardNumber"));
	  }
	
	@Test
    public void testViewAllCreditCards()  throws Exception {
		CreditCardListResponse ccListResponse = new CreditCardListResponse();
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(500.0));
		List<CreditCardRequestParam> ccList = new ArrayList<>();
		ccList.add(ccRequest);
		ccListResponse.setCreditCards(ccList);
		
        Mockito.when(iCreditCardService.getAllCreditCardDetails()).thenReturn(ccListResponse);
        mvc.perform(MockMvcRequestBuilders.get("/getAllCards")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ="))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
	
	@Test
	public void testViewAllCreditCards_InValidUser()  throws Exception {
		CreditCardListResponse ccListResponse = new CreditCardListResponse();
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(500.0));
		List<CreditCardRequestParam> ccList = new ArrayList<>();
		ccList.add(ccRequest);
		ccListResponse.setCreditCards(ccList);
		
        Mockito.when(iCreditCardService.getAllCreditCardDetails()).thenReturn(ccListResponse);
        mvc.perform(MockMvcRequestBuilders.get("/getAllCards")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

}
