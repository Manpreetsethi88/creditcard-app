package com.cards.cc.creditcardprocessor.response;

import java.util.List;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;

public class CreditCardListResponse {
	
	private List<CreditCardDetails> creditCards;
	
	private CreditCardErrorResponse errors;

	public List<CreditCardDetails> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCardDetails> creditCards) {
		this.creditCards = creditCards;
	}

	public CreditCardErrorResponse getErrors() {
		return errors;
	}

	public void setErrors(CreditCardErrorResponse errors) {
		this.errors = errors;
	}

}
