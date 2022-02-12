package com.cards.cc.creditcardprocessor.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardGenericResponse {
	
	private String cardNumber;
	
	private CreditCardErrorResponse errors;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public CreditCardErrorResponse getErrors() {
		return errors;
	}

	public void setErrors(CreditCardErrorResponse errors) {
		this.errors = errors;
	}
	

}
