package com.cards.cc.creditcardprocessor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;
import com.cards.cc.creditcardprocessor.model.CreditCardDetails;
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
		CreditCardDetails creditCardDetails = mock(CreditCardDetails.class);
	 	when(creditCardDetails.getCardNumber()).thenReturn("4111111111111111");
	 	CreditCardGenericResponse ccGenericResponse = creditCardServiceImpl.saveCreditCardDetails(creditCardDetails);
	 	assertNotNull(ccGenericResponse);
	 }
	 
	  @Test
	  public void testAddCardDuplicate() {
		CreditCardDetails creditCardDetails = mock(CreditCardDetails.class);
		when(creditCardDetails.getCardNumber()).thenReturn("5105105105105100");
		when(creditCardDetailsDAO.addCardDetails(creditCardDetails)).thenThrow(DuplicateKeyException.class);
		CreditCardGenericResponse ccGenericResponse = creditCardServiceImpl.saveCreditCardDetails(creditCardDetails);
		assertEquals(CreditCardDetailsConstants.CARD_ALREADY_EXIST, ccGenericResponse.getErrors().getMessage());
	  }
	  
	  @Test
	  public void testGetAllCards() {
	    CreditCardListResponse response = creditCardServiceImpl.getAllCreditCardDetails();
	    assertNull(response.getErrors());
	    }
}
