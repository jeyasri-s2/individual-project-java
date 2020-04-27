package com.sjsu.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sjsu.model.AmExCreditCard;
import com.sjsu.model.CreditCard;
import com.sjsu.model.DiscoverCreditCard;
import com.sjsu.model.MasterCreditCard;
import com.sjsu.model.VisaCreditCard;

public class CardFactoryTest {
	@Test
	public void test_getCreditCardDiscover() {
		System.out.println("Running CardFactory class Testcases : Discovercard creation validation ");

		CreditCard card = CardFactory.getCreditCard(CardType.DISCOVER, 6012000000000000L, "Jeya", "12/21/2020");
		CreditCard discoverCard = new DiscoverCreditCard(6012000000000000L, "Jeya", "12/21/2020");
	
		assertTrue(card.toString().equals(discoverCard.toString()));
	}
	@Test
	public void test_getCreditCardVisa() {
		System.out.println("Running CardFactory class Testcases : Visa card creation validation ");

		CreditCard card = CardFactory.getCreditCard(CardType.VISA, 4120000000000000L, "Bob", "10/1/2022");
		CreditCard visaCard = new VisaCreditCard(4120000000000000L, "Bob", "10/1/2022");
			
		assertTrue(card.toString().equals(visaCard.toString()));
	
	}
	@Test
	public void test_getCreditCardMaster() {
		System.out.println("Running CardFactory class Testcases : Master card creation validation ");

		CreditCard card = CardFactory.getCreditCard(CardType.MASTER, 5310678945000000L, "Matt", "12/11/2021");
		CreditCard masterCard = new MasterCreditCard(5310678945000000L, "Matt", "12/11/2021");
		assertTrue(card.toString().equals(masterCard.toString()));
		
		
	}
	@Test
	public void test_getCreditCardAmex() {
		System.out.println("Running CardFactory class Testcases : AmEx card creation validation ");

		CreditCard card = CardFactory.getCreditCard(CardType.AMEX, 371000000000000L, "Kalai", "09/21/2020");
		CreditCard amexCard = new AmExCreditCard(371000000000000L,"Kalai", "09/21/2020");
		assertTrue(card.toString().equals(amexCard.toString()));
	}

}
