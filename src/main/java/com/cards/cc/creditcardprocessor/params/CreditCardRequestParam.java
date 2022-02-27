package com.cards.cc.creditcardprocessor.params;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;
import com.cards.cc.creditcardprocessor.util.LuhnValidation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CreditCardRequestParam", description = "Credit Card Request Parameters")
public class CreditCardRequestParam {
	
	@NotBlank
	@Pattern(message = "must contain only digits[0-9]", regexp="^[0-9]*$")
	@Size(max = 19 , message = "must be between 0 and 19 digits long")
	@LuhnValidation
	@ApiModelProperty(notes = "Credit Card Number", name="cardNumber", required=true, value="1234567812345678")
	private String cardNumber;
	
	@NotNull
	@ApiModelProperty(notes = "First Name of Credit Card Holder", name="firstName", required=true, value="test FirstName")
	private String firstName;
	
	@ApiModelProperty(notes = "Last Name Credit Card Holder", name="lastName", required=false, value="test LastName")
	private String lastName;
	
	@NotNull
	@ApiModelProperty(notes = "Credit Card Limit Credit Card Holder", name="limit", required=true, value="0.0")
	private BigDecimal limit;
	
	public CreditCardRequestParam() {
		
	}
	public CreditCardRequestParam(CreditCardDetails details) {
		this.cardNumber = details.getCardNumber();
		this.firstName = details.getFirstName();
		this.lastName = details.getLastName();
		this.limit = details.getCreditLimit();
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

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

}
