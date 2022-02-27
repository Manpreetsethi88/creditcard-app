package com.cards.cc.creditcardprocessor.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cards.cc.creditcardprocessor.CreditcardAppApplication;
import com.cards.cc.creditcardprocessor.params.CreditCardRequestParam;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CreditcardAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCardControllerIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	static HttpHeaders headers = new HttpHeaders();

	static {
		headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
		headers.add("Content-Type", "application/json");
	}

	@Test
    public void testAddCardSuccess(){
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("4111111111111111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(2500.0));
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(ccRequest), headers);

        //Call service addition.
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/addCard"),
                HttpMethod.POST, entity, String.class);
        //assert successful creation of new card.
        assertEquals("Successfully added the credit card", HttpStatus.OK, response.getStatusCode());
    }
	
	@Test
    public void testAddCardSuccess_InvalidCreditCard(){
		CreditCardRequestParam ccRequest = new CreditCardRequestParam();
		ccRequest.setCardNumber("41111111111112698s111");
		ccRequest.setFirstName("Manpreet");
		ccRequest.setLastName("Sethi");
		ccRequest.setLimit(new BigDecimal(2500.0));
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>( new Gson().toJson(ccRequest), headers);

        //Call service addition.
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/addCard"),
                HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue("must contain only digits[0-9]",response.getBody().contains("must contain only digits[0-9]"));
        assertTrue("must be between 0 and 19 digits long", response.getBody().contains("must be between 0 and 19 digits long"));
    }

	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/creditcardapp"+ uri;
    }
}
