package com.cards.cc.creditcardprocessor.response;

import java.util.List;

import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardListResponse {
	
	private List<CreditCardRequestParam> creditCards;
	
	private CreditCardErrorResponse errors;

	public List<CreditCardRequestParam> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCardRequestParam> creditCards) {
		this.creditCards = creditCards;
	}

	public CreditCardErrorResponse getErrors() {
		return errors;
	}

	public void setErrors(CreditCardErrorResponse errors) {
		this.errors = errors;
	}

}
