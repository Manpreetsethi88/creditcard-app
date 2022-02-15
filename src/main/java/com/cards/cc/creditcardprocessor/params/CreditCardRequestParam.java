package com.cards.cc.creditcardprocessor.params;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreditCardRequestParam {
	
	@Pattern(message = "must contain only digits[0-9]", regexp="^[0-9]*$")
	@Size(min = 0, max = 19 , message = "must be between 0 and 19 digits long")
	@NotNull
	private String cardNumber;
	
	@NotNull
	private String firstName;
	
	private String lastName;
	
	@NotNull
	private BigDecimal limit;

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

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

}
