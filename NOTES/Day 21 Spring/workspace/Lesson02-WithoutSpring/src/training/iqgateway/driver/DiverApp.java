package training.iqgateway.driver;

import training.iqgateway.business.CurrencyConveter;
import training.iqgateway.business.impl.CurrencyConverterImpl;
import training.iqgateway.business.impl.Customer;

public class DiverApp {
	public static void main(String[] args) {

		// Binding the dependency Since CurrencyConvertorImpl is dependent on Customer
		// First Customer object should be created and injected to
		// CurrencyConvertorImpl

		Customer custRef = new Customer(1001, "Buzz", "Vijaynagar", "9876543210");

		// Need the Instance of business class
		CurrencyConveter cc = new CurrencyConverterImpl(custRef);

		// How to inject [Setter injection or Constructorinjection]
		// Entitles to invoke business operations
		System.out.println(cc.dollarToRS(1000));
	}
}
