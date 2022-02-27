package com.cards.cc.creditcardprocessor.service;

import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;

public interface ICreditCardService {
	
	public CreditCardGenericResponse saveCreditCardDetails(CreditCardRequestParam CreditCardRequestParam);
	
	public CreditCardListResponse getAllCreditCardDetails();

}
