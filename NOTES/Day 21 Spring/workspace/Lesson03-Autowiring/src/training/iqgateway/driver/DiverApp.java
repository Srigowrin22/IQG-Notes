package training.iqgateway.driver;

import org.springframework.context.ApplicationContext;
import somepackage.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import training.iqgateway.business.CurrencyConveter;
import training.iqgateway.business.impl.CurrencyConverterImpl;
import training.iqgateway.business.impl.Customer;

public class DiverApp {
	public static void main(String[] args) {
		// Bring IOC Container into Action [Server]
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/Bean.xml");

		// Lookup for the required business object
		CurrencyConveter cc = (CurrencyConveter) appContext.getBean("ccBean");

		// Now we are entitled to Invoke Business operation
		System.out.println(cc.dollarToRS(1000));
	}
}
