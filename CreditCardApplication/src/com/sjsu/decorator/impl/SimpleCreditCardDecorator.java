package com.sjsu.decorator.impl;

import java.util.List;

import com.sjsu.decorator.ICreditCard;
import com.sjsu.factory.CardType;
import com.sjsu.model.CreditCard;

public class SimpleCreditCardDecorator implements ICreditCard{
	private String fileType;
	
	

	public SimpleCreditCardDecorator(String fileType) {
		super();
		this.fileType = fileType;
	}

	

	@Override
	public List<CreditCard> parseInputFile(String filename) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public CardType validateCreditCard(String creditcardNumber) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeOutputFile(String outFile) {
		// TODO Auto-generated method stub
		
	}

	



	@Override
	public String getOutputFileFormat() {
		// TODO Auto-generated method stub
		return this.fileType;
	}



	@Override
	public CreditCard createCreditCardByType(CardType cardType, String cardNumber, String name, String expDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
