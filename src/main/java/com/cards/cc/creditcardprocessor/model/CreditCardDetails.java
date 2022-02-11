package com.cards.cc.creditcardprocessor.model;

import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;

public class CreditCardDetails {
	
	private String cardNumber;
	
	private String firstName;
	
	private String lastName;
	
	private long creditLimit;

	public String getCardNumber() {
		return cardNumber;
	}
	
	public CreditCardDetails(CreditCardRequestParam params) {
		this.cardNumber = params.getCardNumber();
		this.firstName = params.getFirstName();
		this.lastName = params.getLastName();
		this.creditLimit = params.getLimit();
	}
	
	public CreditCardDetails(String cardNumber, String firstName, String lastName, Long creditLimit) {
		this.cardNumber = cardNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditLimit = creditLimit;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(long creditLimit) {
		this.creditLimit = creditLimit;
	}

}
