package com.cards.cc.creditcardprocessor.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cards.cc.creditcardprocessor.model.CreditCardDetails;

@Repository
public class CreditCardDetailsDAOImpl implements CreditCardDetailsDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int addCardDetails(CreditCardDetails creditCardDetails) throws DuplicateKeyException, DataAccessException {
		return jdbcTemplate.update("insert into CREDIT_CARD_DETAILS (CREDIT_CARD_NO,FIRST_NAME,LAST_NAME,CREDIT_LIMIT) values (?,?,?,?)", 
									creditCardDetails.getCardNumber(), creditCardDetails.getFirstName(), creditCardDetails.getLastName(), 
									creditCardDetails.getCreditLimit()
				        );
	}
	
	@Override
	public List<CreditCardDetails> getAllCards() throws DataAccessException {
		return jdbcTemplate.query("SELECT * FROM CREDIT_CARD_DETAILS", 
							(rs,rowNum) ->
							 new CreditCardDetails(
									 rs.getString("CREDIT_CARD_NO"),
									 rs.getString("FIRST_NAME"),
									 rs.getString("LAST_NAME"),
									 rs.getLong("CREDIT_LIMIT")
							));
	}

}
