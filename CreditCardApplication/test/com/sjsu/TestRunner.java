package com.sjsu;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.sjsu.decorator.impl.CreditCardDecoratorTest;
import com.sjsu.factory.CardFactoryTest;
public class TestRunner {
	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(CreditCardDecoratorTest.class, CardFactoryTest.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println("Failures: "+failure.toString());
	      }
			
	      System.out.println("Success: "+result.wasSuccessful());
	   }
}
