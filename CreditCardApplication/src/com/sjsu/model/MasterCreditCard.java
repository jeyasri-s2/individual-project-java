package com.sjsu.model;

import java.util.Date;

public class MasterCreditCard extends CreditCard {
	
	private long cardNumber;
	private String cardHolderName;
	private String cardExpirationDate;
	
	
	public MasterCreditCard(long cardNumber, String cardHolderName, String cardExpirationDate) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.cardExpirationDate = cardExpirationDate;
		
	}


	@Override
	public long getCardNumber() {
		// TODO Auto-generated method stub
		return this.cardNumber;
	}


	@Override
	public String getCardHolderName() {
		// TODO Auto-generated method stub
		return this.cardHolderName;
	}


	@Override
	public String getCardExpirationDate() {
		// TODO Auto-generated method stub
		return this.cardExpirationDate;
	}



}
