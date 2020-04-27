package com.sjsu.decorator.impl;

import com.sjsu.decorator.ICreditCard;
import com.sjsu.factory.CardType;
import com.sjsu.model.CreditCard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONFileDecorator extends CreditCardDecorator {
	
	private List<String[]> data = null;
	private List<CreditCard> creditCards = null;

	public JSONFileDecorator(ICreditCard creditCard) {
		super(creditCard);
	}

	@Override
	public List<CreditCard> parseInputFile(String filename) {
		JSONParser parser = new JSONParser();
		
		String cardNum = "";
		String expDate = "";
		String name = "";
	    data = new ArrayList<String[]>();
	    creditCards = new ArrayList<CreditCard>();
		try {
			Object obj = parser.parse(new FileReader(filename));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONArray jsonArray = (JSONArray) obj;
			
			Iterator<JSONObject> ccIterator = jsonArray.iterator();
			while (ccIterator.hasNext()) {
				JSONObject jsonObject = ccIterator.next();
				cardNum = jsonObject.get("CardNumber").toString();
				expDate = jsonObject.get("ExpirationDate").toString();
				name =  jsonObject.get("NameOfCardholder").toString();
				CardType cardType = validateCreditCard(cardNum);
		          
		          if(cardType != CardType.ERROR) {
		        	 // long cardNumber = Long.parseLong(cardNum);
		        	  CreditCard card = super.createCreditCardByType(cardType, cardNum, name, expDate);
		        	  creditCards.add(card);
		        	  
		        	  data.add(new String[] { cardNum, cardType.getMessage(), "None" });
		        	  System.out.println(" Cardtype is not error  "+data.size());

		          }
		          else
		          {
		        	  data.add(new String[] { cardNum, cardType.getMessage(), "InvalidCardNumber" });
		          }
		          
	
			
			}
			
			
		} 
			catch (Exception e) {
			e.printStackTrace();
		}
		return creditCards;
		
	}

	@Override
	public CardType validateCreditCard(String creditcardNumber) {
		return super.validateCreditCard(creditcardNumber);
		
	}

	@Override
	public void writeOutputFile(String outFile) {
        JSONArray jsonArray = new JSONArray();

	
        for(String[] row: data) {

	        JSONObject jsonObject = new JSONObject();
	        if(row[2].equalsIgnoreCase("none")) { // valid card 
		        jsonObject.put("CardNumber", Long.parseLong(row[0]));
		        jsonObject.put("CardType", row[1]);
		        jsonObject.put("Error", row[2]);
		        jsonArray.add(jsonObject);
	        }
	        else
	        {
	        	 jsonObject.put("CardNumber",row[0]);
			     jsonObject.put("CardType", row[1]);
			     jsonObject.put("Error", row[2]);
			     jsonArray.add(jsonObject);
	        }

        }
       
       
         
        //Write JSON file
        try (FileWriter file = new FileWriter(outFile)) {
 
            file.write(jsonArray.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}



}
