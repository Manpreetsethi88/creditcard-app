package com.cards.cc.creditcardprocessor.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreditCardValidator  implements ConstraintValidator<LuhnValidation, String>{
	
	public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
		return luhnCheck(cardNumber);
	}
	
	//Returns true if given card number is valid
	public static boolean luhnCheck(String cardNo) {
        
		int nDigits = cardNo.length();
		int nSum = 0;
		boolean isSecond = false;
		for(int i = nDigits-1 ; i >= 0 ; i--) {
			int d = cardNo.charAt(i) - '0';
			if(isSecond == true)
				d = d*2;
			//add the two digits if we get 2-digit number after doubling
			nSum += d/10;
			nSum += d%10;
			isSecond = !isSecond;
		}
		return(nSum % 10 == 0);
	}
}
