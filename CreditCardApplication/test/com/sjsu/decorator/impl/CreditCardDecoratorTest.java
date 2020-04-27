package com.sjsu.decorator.impl;

import org.junit.Test;

import com.sjsu.factory.CardType;

import static org.junit.Assert.*;

import org.junit.BeforeClass;


public class CreditCardDecoratorTest {
	private static CreditCardDecorator creditCardDecorator;
	
	 @BeforeClass
	 public static void beforeClass() {
		 System.out.println("Running creditcardDecorator class testcases");
		 creditCardDecorator = new CreditCardDecorator(new SimpleCreditCardDecorator("csv"));
	 }
	
	
	@Test
	public void testValidateCreditCard_Discover() {
		
		System.out.println("Running Credit card discover card validations ");
		String creditcardNumber = "6011000000000000";
		
		CardType type = creditCardDecorator.validateCreditCard(creditcardNumber);
	     //Check that two objects are equal
	    assertEquals(type, CardType.DISCOVER);
		
	}
	@Test
	public void testValidateCreditCard_Visa() {
		System.out.println("Running Credit card Visa card validations ");

		
		String creditcardNumber = "4120000000000";
		
		CardType type = creditCardDecorator.validateCreditCard(creditcardNumber);
	     //Check that two objects are equal
	    assertEquals(type, CardType.VISA);
		
	}
	
	@Test
	public void testValidateCreditCard_AmEx() {
		System.out.println("Running Credit card Amex card validations ");

		String creditcardNumber = "341000000000000";
		
		CardType type = creditCardDecorator.validateCreditCard(creditcardNumber);
	     //Check that two objects are equal
	    assertEquals(type, CardType.AMEX);
		
	}
	
	@Test
	public void testValidateCreditCard_Master() {
		System.out.println("Running Credit card Master card validations ");

		String creditcardNumber = "5210678945000000";
		
		CardType type = creditCardDecorator.validateCreditCard(creditcardNumber);
	     //Check that two objects are equal
	    assertEquals(type, CardType.MASTER);
		
	}
	
	@Test
	public void testGetSize() {
		System.out.println("Running GetSize support method validation ");

		int size = CreditCardDecorator.getSize(109808965);
		assertEquals(size, 9);
		
	}
	@Test
	public void testPrefixMatched() {
		System.out.println("Running PrefixMatched support method validation ");

		boolean prefix = CreditCardDecorator.prefixMatched(67589575,6);
		assertTrue(prefix);
		prefix = CreditCardDecorator.prefixMatched(12589575,6);
		assertFalse(prefix);
		
	}
	
	@Test
	public void testGetPrefix() {
		System.out.println("Running getPrefix support method validation ");

		long prefix = CreditCardDecorator.getPrefix(67589575, 4);
		assertEquals(prefix, 6758);
		prefix = CreditCardDecorator.getPrefix(12389575, 3);
		assertEquals(prefix, 123);
		
		
	}
	
	@Test
	public void test_isValidMasterCard() {
		System.out.println("Running Mastercard support method validation ");

		boolean valid = creditCardDecorator.isValidMasterCard("5110678945000000",5110678945000000L);
		assertTrue(valid);
		valid = creditCardDecorator.isValidMasterCard("5310678945000000",5310678945000000L);
		assertTrue(valid);
		
		valid = creditCardDecorator.isValidMasterCard("5610600045000000",5610600045000000L);
		assertFalse(valid);
		valid = creditCardDecorator.isValidMasterCard("561060004500000",561060004500000L);
		assertFalse(valid);
		
		
		
	}
	@Test
	public void test_isValidVisaCard() {
		System.out.println("Running Visa card support method validation ");

		boolean valid = creditCardDecorator.isValidVisaCard(4120000000000L);
		assertTrue(valid);
		valid = creditCardDecorator.isValidVisaCard(40003400L);
		assertFalse(valid);
		valid = creditCardDecorator.isValidVisaCard(412000000000L);
		assertFalse(valid);
		
		
	}
	@Test
	public void test_isValidDiscoverCard() {
		System.out.println("Running Discover card support method validation ");

		boolean valid = creditCardDecorator.isValidDiscoverCard(6011000000000000L);
		assertTrue(valid);
		valid = creditCardDecorator.isValidDiscoverCard(601100000000000L);
		assertFalse(valid);
		valid = creditCardDecorator.isValidDiscoverCard(601100000L);
		assertFalse(valid);
		
		
	}
	@Test
	public void test_isValidAmexCard() {
		System.out.println("Running Amex card support method validation ");

		boolean valid = creditCardDecorator.isValidAmexCard("341000000000000",341000000000000L);
		assertTrue(valid);
		valid = creditCardDecorator.isValidAmexCard("371000000000000",371000000000000L);
		assertTrue(valid);
		
		valid = creditCardDecorator.isValidAmexCard("381000000000000",381000000000000L);
		assertFalse(valid);
		valid = creditCardDecorator.isValidAmexCard("38100000000000",38100000000000L);
		assertFalse(valid);
		
		
		
	}
	
	

}
