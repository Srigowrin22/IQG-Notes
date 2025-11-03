package training.iqgateway.business.impl;

import training.iqgateway.business.CurrencyConveter;

public class CurrencyConverterImpl implements CurrencyConveter {

	// CurrencyConverterImpl Having Dependency of Customer
	private Customer customer;

	public CurrencyConverterImpl() {

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CurrencyConverterImpl(Customer customer) {
		super();
		this.customer = customer;
		System.out.println("Parameterized Constructor called!");
	}

	@Override
	public String dollarToRS(double dollar) {
		double result = 69.23 * dollar;
		return "Welcome " + customer.getName() + ", CurrencyConverter value is: " + result;
	}

	@Override
	public String poundsToRS(double pounds) {
		double result = 81.56 * pounds;
		return "Welcome " + customer.getName() + ", CurrencyConverter value is: " + result;
	}

	@Override
	public String riyalsToRS(double riyals) {
		double result = 17.89 * riyals;
		return "Welcome " + customer.getName() + ", CurrencyConverter value is: " + result;
	}

	@Override
	public String yensToRS(double yens) {
		double result = 0.5 * yens;
		return "Welcome " + customer.getName() + ", CurrencyConverter value is: " + result;
	}

	@Override
	public String pesosToRS(double pesos) {
		double result = 0.35 * pesos;
		return "Welcome " + customer.getName() + ", CurrencyConverter value is: " + result;
	}

}
