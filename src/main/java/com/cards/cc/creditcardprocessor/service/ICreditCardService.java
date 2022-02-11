package com.cards.cc.creditcardprocessor.service;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;

public interface ICreditCardService {
	
	public CreditCardGenericResponse saveCreditCardDetails(CreditCardDetails creditCardDetails);
	
	public CreditCardListResponse getAllCreditCardDetails();

}
