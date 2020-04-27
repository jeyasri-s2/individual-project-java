package com.sjsu.model;


public abstract class CreditCard {
	
	
	public abstract long getCardNumber();
	public abstract String getCardHolderName();
	public abstract String getCardExpirationDate();
	
	@Override
	public String toString(){
		return "card number= "+this.getCardNumber()+", card holder name="+this.getCardHolderName()+", Expiration Date="+this.getCardExpirationDate();
	}
		
	

	

}
