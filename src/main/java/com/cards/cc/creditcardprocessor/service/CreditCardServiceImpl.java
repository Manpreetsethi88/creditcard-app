package com.cards.cc.creditcardprocessor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;
import com.cards.cc.creditcardprocessor.repository.CreditCardDetailsDAO;
import com.cards.cc.creditcardprocessor.response.CreditCardGenericResponse;
import com.cards.cc.creditcardprocessor.response.CreditCardListResponse;

@Service
public class CreditCardServiceImpl implements ICreditCardService {
	
	@Autowired
	private CreditCardDetailsDAO  creditCardDetailsDAO;
		
	public CreditCardGenericResponse saveCreditCardDetails(CreditCardDetails creditCardDetails) throws DuplicateKeyException, DataAccessException{
		CreditCardGenericResponse ccGenericResponse = new CreditCardGenericResponse();
		ccGenericResponse.setCardNumber(EncryptionService.decrypt(creditCardDetails.getCardNumber()));
		creditCardDetailsDAO.addCardDetails(creditCardDetails);
		return ccGenericResponse;
	}
		
	public CreditCardListResponse getAllCreditCardDetails() throws DataAccessException{
		CreditCardListResponse ccListResponse = new CreditCardListResponse();
		List<CreditCardDetails> allCreditCards = creditCardDetailsDAO.getAllCards();
		ccListResponse.setCreditCards(allCreditCards);
		return ccListResponse;
	}

}