package com.sjsu.decorator;

import java.util.List;

import com.sjsu.factory.CardType;
import com.sjsu.model.CreditCard;

public interface ICreditCard {
	List<CreditCard> parseInputFile(String filename);
	CardType validateCreditCard(String creditcardNumber);
	void writeOutputFile(String outFile);
	
	String getOutputFileFormat();
	CreditCard createCreditCardByType(CardType cardType, String cardNumber, String name, String expDate);

}
