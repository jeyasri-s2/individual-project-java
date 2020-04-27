package com.sjsu.decorator.impl;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sjsu.decorator.ICreditCard;
import com.sjsu.factory.CardFactory;
import com.sjsu.factory.CardType;
import com.sjsu.model.CreditCard;

public class CreditCardDecorator implements ICreditCard{
	
	private ICreditCard creditCard;
	protected List<CreditCard> creditCards = null;
	
	

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public CreditCardDecorator(ICreditCard creditCard) {
		super();
		this.creditCard = creditCard;
		
	}

	@Override
	public List<CreditCard> parseInputFile(String filename) {
		return this.creditCard.parseInputFile(filename);
		
	}

	@Override
	public CardType validateCreditCard(String creditcardNumber) {
		
		CardType type = CardType.ERROR;
		
		String regex = "^[0-9]+$";
		 
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(creditcardNumber);
		if(matcher.matches()) {
			try {
				long number = Long.parseLong(creditcardNumber);
				
				boolean masterCardValidation = prefixMatched(number,5);
				boolean visaCardValidation = prefixMatched(number,4);
				boolean amexCardValidation = prefixMatched(number,3);
				boolean discoverCardValidation = prefixMatched(number,6011);
				
				
				if(masterCardValidation && isValidMasterCard(creditcardNumber, number)) {
					
					type = CardType.MASTER;
				}
				else if(visaCardValidation && isValidVisaCard(number)) {
					
					type = CardType.VISA;
				}
				else if(amexCardValidation && isValidAmexCard(creditcardNumber, number)) {
				
					type = CardType.AMEX;		
					
				}
				else if(discoverCardValidation && isValidDiscoverCard(number) ) {
					type = CardType.DISCOVER;
				}
				else {
					type = CardType.ERROR;
				}
			}
			catch(NumberFormatException p) {
				type = CardType.ERROR;
			}
		}
		
		return type;
		
		
	}
	
	// Return the number of digits in d 
    public static int getSize(long d) 
    { 
        String num = d + ""; 
        return num.length(); 
    } 

  // Return true if the digit d is a prefix for number 
    public static boolean prefixMatched(long number, int d) 
    { 
        return getPrefix(number, getSize(d)) == d; 
    } 
    // Return the first k number of digits from  
    // number. If the number of digits in number 
    // is less than k, return number. 
    public static long getPrefix(long number, int k) 
    { 
        if (getSize(number) > k) { 
            String num = number + ""; 
            return Long.parseLong(num.substring(0, k)); 
        } 
        return number; 
    } 
    
    
   public boolean isValidMasterCard(String creditcardNumber, long number) {
	   int secondDigit = Integer.parseInt(creditcardNumber.substring(1, 2));
		if((secondDigit >= 1 && secondDigit <=5 ) && (getSize(number) == 16)) {
			return true;
		}		
		
		return false;
	   
   }
   public boolean isValidVisaCard(long number) {
	   if((getSize(number) == 13) || (getSize(number) == 16)) {
			return true;
		}
	   return false;
   }
   public boolean isValidDiscoverCard(long number) {
	   return (getSize(number) == 16);
	   
	   
   }
   public boolean isValidAmexCard(String creditcardNumber, long number) {
	   int secondDigit = Integer.parseInt(creditcardNumber.substring(1, 2));
		if((secondDigit == 4 || secondDigit == 7 ) && (getSize(number) == 15)) {
			return true;
		}
	   return false;
   }
    
    
    
	@Override
	public void writeOutputFile(String outFile) {
		//System.out.println("writeOutputFile in CreditCardDecorator");
		this.creditCard.writeOutputFile(outFile);
	}

	

	@Override
	public String getOutputFileFormat() {
		return creditCard.getOutputFileFormat();
	}

	@Override
	public CreditCard createCreditCardByType(CardType cardType, String cardNum, String name, String expDate) {
		
		long cardNumber = Long.parseLong(cardNum);
    	CreditCard card = CardFactory.getCreditCard(cardType, cardNumber, name, expDate);
		return card;
	}

}
