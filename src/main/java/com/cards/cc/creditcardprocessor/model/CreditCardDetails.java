package com.cards.cc.creditcardprocessor.model;

import java.math.BigDecimal;

import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.cards.cc.creditcardprocessor.service.EncryptionService;

public class CreditCardDetails {
	
	private String cardNumber;
	
	private String firstName;
	
	private String lastName;
	
	private BigDecimal creditLimit;
	
	public CreditCardDetails() {
		
	}
	
	public CreditCardDetails(CreditCardRequestParam params) {
		this.cardNumber = EncryptionService.encrypt(params.getCardNumber());
		this.firstName = params.getFirstName();
		this.lastName = params.getLastName();
		this.creditLimit = params.getLimit();
	}
	
	public CreditCardDetails(String cardNumber, String firstName, String lastName, BigDecimal creditLimit) {
		this.cardNumber = EncryptionService.decrypt(cardNumber);
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditLimit = creditLimit;
	}
	
	public String getCardNumber() {
		return cardNumber;
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

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

}
