package com.cards.cc.creditcardprocessor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;
import com.cards.cc.creditcardprocessor.model.CreditCardDetails;
import com.cards.cc.creditcardprocessor.repository.CreditCardDetailsDAO;
import com.cards.cc.creditcardprocessor.response.CreditCardErrorResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;

@Service
public class CreditCardServiceImpl implements ICreditCardService {
	
	@Autowired
	private CreditCardDetailsDAO  creditCardDetailsDAO;
		
	public CreditCardGenericResponse saveCreditCardDetails(CreditCardDetails creditCardDetails) {
		CreditCardGenericResponse ccGenericResponse = new CreditCardGenericResponse();
		ccGenericResponse.setCardNumber(EncryptionService.decrypt(creditCardDetails.getCardNumber()));
		try {
			creditCardDetailsDAO.addCardDetails(creditCardDetails);
		} catch (DuplicateKeyException e) {
			CreditCardErrorResponse errors = new CreditCardErrorResponse();
			errors.setMessage(CreditCardDetailsConstants.CARD_ALREADY_EXIST);
			ccGenericResponse.setErrors(errors);
		} catch (DataAccessException e) {
			CreditCardErrorResponse errors = new CreditCardErrorResponse();
			errors.setMessage(CreditCardDetailsConstants.GENERIC_ERROR_MESSAGE);
			ccGenericResponse.setErrors(errors);
		}
		return ccGenericResponse;
	}
		
	public CreditCardListResponse getAllCreditCardDetails() {
		CreditCardListResponse ccListResponse = new CreditCardListResponse();
		try {
			List<CreditCardDetails> allCreditCards = creditCardDetailsDAO.getAllCards();
			ccListResponse.setCreditCards(allCreditCards);
		} catch (DataAccessException e) {
			CreditCardErrorResponse errors = new CreditCardErrorResponse();
			errors.setMessage(CreditCardDetailsConstants.GENERIC_ERROR_MESSAGE);
			ccListResponse.setErrors(errors);
		}
		return ccListResponse;
	}

}