package com.cards.cc.creditcardprocessor.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardValidatorTest {
	
	@Test
    public void testLuhnCheckValid() throws Exception {
        String number = "4111111111111111";
        assertEquals(CreditCardValidator.luhnCheck(number), true);
    }

    @Test
    public void test_luhnCheckInvalid() throws Exception {
        String number = "4111111111111121";
        assertEquals(CreditCardValidator.luhnCheck(number), false);
    }
}
