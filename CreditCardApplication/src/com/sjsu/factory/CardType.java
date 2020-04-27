package com.sjsu.factory;



public enum CardType 
{
	MASTER("MasterCard"), 
	DISCOVER("Discover"), 
	AMEX("AmericanExpress"), 
	VISA("Visa"),
	ERROR("Invalid");
 
    private String message;
 
    CardType(String message) {
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }
}