package com.cards.cc.creditcardprocessor.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cards.cc.creditcardprocessor.constants.CreditCardDetailsConstants;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CreditCardValidator.class)
public @interface LuhnValidation {
	
	//error message
	public String message() default CreditCardDetailsConstants.CARD_NUMBER_ERROR;
	//represents the group of constraints
	public Class<?>[] groups() default {};
	//represents additional information about annotation
	public Class<? extends Payload>[] payload() default {};

}
