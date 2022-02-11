package com.cards.cc.creditcardprocessor.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;

public interface CreditCardDetailsDAO {
	
	public int addCardDetails(CreditCardDetails creditCardDetails) throws DuplicateKeyException, DataAccessException;
	
	public List<CreditCardDetails> getAllCards() throws DataAccessException;
}
