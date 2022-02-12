package com.cards.cc.creditcardprocessor.params;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreditCardRequestParam {
	
	@Pattern(message = "Credit Card Number must contain only digits[0-9]", regexp="^[0-9]*$")
	@Size(min = 0, max = 19 , message = "Credit Card Number must be between 0 and 19 digits long")
	private String cardNumber;
	
	private String firstName;
	
	private String lastName;
	
	private long limit;

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

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

}
