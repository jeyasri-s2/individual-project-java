package com.sjsu;
import java.util.Arrays;
import java.util.List;

import com.sjsu.decorator.ICreditCard;
import com.sjsu.decorator.impl.CSVFileDecorator;
import com.sjsu.decorator.impl.CreditCardDecorator;
import com.sjsu.decorator.impl.JSONFileDecorator;
import com.sjsu.decorator.impl.SimpleCreditCardDecorator;
import com.sjsu.decorator.impl.XMLFileDecorator;
import com.sjsu.model.CreditCard;

public class CreditCardMainApplication {

	public static void main(String[] args)
	{
		
		
		if(args.length != 2) {
			System.out.println("Please enter valid input : java CreditCardMainApplication <input_file> <output_file>");
			return;
		}
		String inputFilename = args[0];
		String outputFilename = args[1];
		String filenameArray[] = inputFilename.split("\\.");
		String inputExtension = filenameArray[filenameArray.length - 1];
		
		filenameArray = outputFilename.split("\\.");
		String outputExtension = filenameArray[filenameArray.length - 1];
		
		if(!inputExtension.equalsIgnoreCase(outputExtension)) {
			System.out.println("Please enter valid input : input and output filenames should have same extension");
			return;
		}
		
		String[] allowed_file_extensions =  {"csv","CSV","json","JSON", "xml","XML"};
		List<String> fileExtensions = Arrays.asList(allowed_file_extensions);
		
		if(fileExtensions.contains(inputExtension))
		{
			
			ICreditCard card = null;
			
			switch(inputExtension) 
			{
			case "csv":
			case "CSV":
				card = new CreditCardDecorator(
		                 new CSVFileDecorator(new SimpleCreditCardDecorator("csv")));
				List<CreditCard> creditCards = card.parseInputFile(inputFilename);
				card.writeOutputFile(outputFilename);
				
				System.out.println("creditCards objects "+creditCards);
				System.out.println(" Execution completed: Please check output file for results ");

				break;
			case "xml":
			case "XML":
				card = new CreditCardDecorator(
		                 new XMLFileDecorator(new SimpleCreditCardDecorator("xml")));
				creditCards = card.parseInputFile(inputFilename);
				card.writeOutputFile(outputFilename);
				System.out.println(card.getOutputFileFormat());
				System.out.println("creditCards objects "+creditCards);
				System.out.println(" Execution completed: Please check output file for results ");

				break;
				
			case "json":
			case "JSON":
				card = new CreditCardDecorator(
		                 new JSONFileDecorator(new SimpleCreditCardDecorator("json")));
				creditCards = card.parseInputFile(inputFilename);
				card.writeOutputFile(outputFilename);
				System.out.println(card.getOutputFileFormat());
				System.out.println("creditCards objects "+creditCards);
				
				System.out.println(" Execution completed: Please check output file for results ");
				
				break;
			default:
				break;
					
				
			}
			
		}
		else
		{
			System.out.println("Invalid input "+inputFilename+" , current system supports only "+fileExtensions);
		}

		
		
	}
	
}
