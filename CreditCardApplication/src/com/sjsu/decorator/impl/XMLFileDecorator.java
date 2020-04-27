package com.sjsu.decorator.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sjsu.decorator.ICreditCard;
import com.sjsu.factory.CardType;
import com.sjsu.model.CreditCard;

public class XMLFileDecorator extends CreditCardDecorator {
	
	private List<String[]> data = null;
	private List<CreditCard> creditCards = null;

	public XMLFileDecorator(ICreditCard creditCard) {
		super(creditCard);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CreditCard> parseInputFile(String filename) {
		try {

			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			String cardNum = "";
			String expDate = "";
			String name = "";
			
		    data = new ArrayList<String[]>();
		    creditCards = new ArrayList<CreditCard>();

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("row");

		

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					expDate =  eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();
					name =  eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();
					cardNum = eElement.getElementsByTagName("CardNumber").item(0).getTextContent();

				}
				
				CardType cardType = validateCreditCard(cardNum);
	
		          
		          if(cardType != CardType.ERROR) {
		        	 // long cardNumber = Long.parseLong(cardNum);
		        	 // CreditCard card = CardFactory.getCreditCard(cardType, cardNumber, name, expDate);
		        	 
		        	  CreditCard card = super.createCreditCardByType(cardType, cardNum, name, expDate);
		        	  creditCards.add(card);
		        	  data.add(new String[] { cardNum, cardType.getMessage(), "None" });

		          }
		          else
		          {
		        	  data.add(new String[] { cardNum, cardType.getMessage(), "InvalidCardNumber" });
		          }
		          

					
				}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		return creditCards;
		
	}

	@Override
	public CardType validateCreditCard(String creditcardNumber) {
		return super.validateCreditCard(creditcardNumber);
		
	}

	private static Node getCreditCard(Document doc, String[] creditCard) {
        Element company = doc.createElement("row");
       
        company.appendChild(getCreditCardElements(doc, company, "CardNumber", creditCard[0]));
        company.appendChild(getCreditCardElements(doc, company, "CardType", creditCard[1]));
        company.appendChild(getCreditCardElements(doc, company, "Error", creditCard[2]));
        return company;
    }
 
    // utility method to create text node
    private static Node getCreditCardElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
	@Override
	public void writeOutputFile(String outFile) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElementNS("", "root");
            doc.appendChild(mainRootElement);
            
            for(String[] row: data) {
            	 mainRootElement.appendChild(getCreditCard(doc, row));
            }

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            DOMSource source = new DOMSource(doc);
            StreamResult console = new StreamResult(System.out);
            
            
            StreamResult file = new StreamResult(new File(outFile));

            //write data
            transformer.transform(source, console);
            transformer.transform(source, file);
 
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	

}
