package com.sjsu.factory;


import com.sjsu.model.AmExCreditCard;
import com.sjsu.model.CreditCard;
import com.sjsu.model.DiscoverCreditCard;
import com.sjsu.model.MasterCreditCard;
import com.sjsu.model.VisaCreditCard;

public class CardFactory {
	
	public static CreditCard getCreditCard(CardType type, long cardNumber, String cardHolderName, String cardExpirationDate){
		
		CreditCard card = null;
		
		switch(type) {
		case MASTER:
			card = new MasterCreditCard(cardNumber,cardHolderName, cardExpirationDate);
			break;
		case DISCOVER:
			card = new DiscoverCreditCard(cardNumber,cardHolderName, cardExpirationDate);
			break;
			
		case AMEX:
			card = new AmExCreditCard(cardNumber,cardHolderName, cardExpirationDate);
			break;
			
		case VISA:
			card = new VisaCreditCard(cardNumber,cardHolderName, cardExpirationDate);
			break;
			
		default:
			break;
		
		}
		
		return card;
	}
}


