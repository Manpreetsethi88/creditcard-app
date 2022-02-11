package com.cards.cc.creditcardprocessor.util;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;

public class CreditCardValidator {
	
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
	
	public static boolean validateCardDetails(CreditCardDetails creditCardDetails) {
		boolean cardResult = true;
		try {
			if(creditCardDetails.getCardNumber().length() > 19 || Long.parseLong(creditCardDetails.getCardNumber()) < 0) {
				cardResult = false;
			}
		} catch(NumberFormatException e) {
			cardResult = false;
		}
		return cardResult;
	}

}
