package oe;

/**
 * Represents an individual customer that extends the base Customer class.
 * This class adds individual-specific information including a license number.
 * 
 * <p>The class inherits basic customer properties (name, address, phone) 
 * from the Customer class and adds one additional property:
 * - license number (licNumber)
 * 
 * <p>Example usage:
 * <pre>
 * Individual customer = new Individual("John Doe", "123 Main St", "555-1234", "DL12345");
 * String license = customer.getLicNumber();
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Customer
 */
public class Individual extends Customer {

    /**
     * The license number associated with this individual customer
     */
    private String licNumber;

    /**
     * Default constructor that creates an Individual with default values.
     * Calls the superclass's default constructor and initializes
     * license number to null.
     */
    public Individual() {
        super();
    }

    /**
     * Creates an Individual customer with specified details.
     * 
     * @param name The name of the individual
     * @param address The address of the individual
     * @param phone The phone number of the individual
     * @param licNumber The license number of the individual
     */
    public Individual(String name, String address, String phone, String licNumber) {
        super(name, address, phone);
        this.licNumber = licNumber;
    }

    /**
     * Gets the license number of this individual customer
     * 
     * @return The license number string
     */
    public String getLicNumber() {
        return licNumber;
    }

    /**
     * Returns a string representation of the Individual object.
     * Currently delegates to the superclass's toString() method.
     * 
     * @return A string representation of the Individual
     */
    @Override
    public String toString() {
        return super.toString();
    }
}