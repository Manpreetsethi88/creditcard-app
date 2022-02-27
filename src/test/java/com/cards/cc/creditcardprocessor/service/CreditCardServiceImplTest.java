package com.cards.cc.creditcardprocessor.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.cards.cc.creditcardprocessor.repository.CreditCardDetailsDAO;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceImplTest {
	
	@Mock
	CreditCardDetailsDAO creditCardDetailsDAO;
	
	@InjectMocks
    private CreditCardServiceImpl creditCardServiceImpl;
	
	 @Test
	 public void testAddCardSuccess() {
		CreditCardRequestParam creditCardParams = mock(CreditCardRequestParam.class);
	 	when(creditCardParams.getCardNumber()).thenReturn(EncryptionService.encrypt("4111111111111111"));
	 	CreditCardGenericResponse ccGenericResponse = creditCardServiceImpl.saveCreditCardDetails(creditCardParams);
	 	assertNotNull(ccGenericResponse);
	 }
	  
	  @Test
	  public void testGetAllCards() {
	    CreditCardListResponse response = creditCardServiceImpl.getAllCreditCardDetails();
	    assertNull(response.getErrors());
	    }
}
