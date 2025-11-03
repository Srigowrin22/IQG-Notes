/**
 * Customer class is used to manage Customer Details in order entry app
 */

public class Customer

{	/**
	 * ID Attribute holds Customer Id in application
	 */

	public int id;

	/**
	 * Name Attribute holds Customer Name in application
	 */

	public String name;

	/**
	 * Address Attribute holds Customer Name in application
	 */

	public String address;

	/**
	 * Phone Attribute holds Customer Name in application
	 */

	public String phone;

	/**
	 * This is Default / no Argument constructor
	 */

	public Customer(){}

	/**
	 * sayHello method says Hello to everyone!
	 */

	public void sayHello()
	{
		System.out.print(" Hello From Customer ");
	}
}