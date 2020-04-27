package com.sjsu.decorator.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sjsu.decorator.ICreditCard;
import com.sjsu.factory.CardFactory;
import com.sjsu.factory.CardType;
import com.sjsu.model.CreditCard;

public class CSVFileDecorator extends CreditCardDecorator {
	
	
	private List<CreditCard> creditCards = null;
	private List<String[]> data = null;

	public CSVFileDecorator(ICreditCard creditCard) {
		super(creditCard);
	}

	@Override
	public List<CreditCard> parseInputFile(String filename) {
		System.out.println("CSVFileDecorator parseInput");
		BufferedReader br = null;
	    String line = "";
	    String cvsSplitBy = ",";
	    String cardNum = "";
	    String expDate = "";
	    String name = "";
	    creditCards = new ArrayList<CreditCard>(); 
	    data = new ArrayList<String[]>();
	    data.add(new String[] { "CardNumber", "CardType", "Error"}); 

	    try {
	    	br = new BufferedReader(new FileReader(filename));
	    	// skip the first line for header 
	    	line = br.readLine();
	    	
	        while ((line = br.readLine()) != null) {
	        	// use comma as separator
	          String[] columns = line.split(cvsSplitBy);
	          if(columns.length >= 3) {
	        	  cardNum = columns[0];
	        	  expDate = columns[1];
	        	  name = columns[2];
	        	  
	          }
	          CardType cardType = validateCreditCard(cardNum);   
	          
	          if(cardType != CardType.ERROR) {
	        	  //long cardNumber = Long.parseLong(cardNum);
	        	  CreditCard card = super.createCreditCardByType(cardType, cardNum, name, expDate);
	        	  creditCards.add(card);
	        	  data.add(new String[] { cardNum, cardType.getMessage(), "None" });
	          }
	          else
	          {
	        	  data.add(new String[] { cardNum, cardType.getMessage(), "InvalidCardNumber" });
	          }
	          

	         

	          

	        }

	      } catch (FileNotFoundException e) {
	            e.printStackTrace();
	     } catch (IOException e) {
	            e.printStackTrace();
	     } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	     }
		return creditCards;
		
	}

	@Override
	public CardType validateCreditCard(String creditcardNumber) {
		return super.validateCreditCard(creditcardNumber);
		
	}

	@Override
	public void writeOutputFile(String outFile) {
		//super.writeOutputFile(outFile);
		System.out.println("writeOutputFile in CSVCreditCardDecorator");
		
		String COMMA_DELIMITER = ",";
	    String NEW_LINE_SEPARATOR = "\n";
		
		// first create file object for file placed at location 
	    // specified by filepath 
	    try {
	    FileWriter fileWriter = new FileWriter(outFile); 
	  
	    try { 
	        // create FileWriter object with file as parameter 
	    	
            //Write a new student object list to the CSV file
            for (String[] line : data) {
                fileWriter.append(line[0]);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(line[1]);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(line[2]);
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
	     
		    } catch (Exception e) {
		        System.out.println("Error in CsvFileWriter !!!");
		        e.printStackTrace();
		    } finally {
		         
		        try {
		            fileWriter.flush();
		            fileWriter.close();
		        } catch (IOException e) {
		            System.out.println("Error while flushing/closing fileWriter !!!");
		            e.printStackTrace();
		        }
		         
		    }
	    }
	    catch(IOException exp) {
	    	System.out.println("Unable to write to output file. Pleas esnsure path exists");
	    }
		
	}


}
