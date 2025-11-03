package oe;

/**
 * Represents a company customer that extends the basic Customer class.
 * This class adds company-specific information including a contact person
 * and a discount percentage that the company is eligible for.
 * 
 * The class inherits basic customer properties (name, address, phone) 
 * from the Customer class and adds two additional properties:
 * - contact person name
 * - discount percentage
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Customer
 */
public class Company extends Customer {
    
    /**
     * The name of the contact person at the company
     */
    private String contact;
    
    /**
     * The discount percentage the company is eligible for
     */
    private int discount;
    
    /**
     * Default constructor that creates a Company with default values.
     * Calls the superclass's default constructor and initializes
     * contact to null and discount to 0.
     */
    public Company() {
        super();
    }

    /**
     * Creates a Company with specified details.
     * 
     * @param name The name of the company
     * @param address The address of the company
     * @param phone The phone number of the company
     * @param contact The name of the contact person at the company
     * @param discount The discount percentage the company is eligible for
     */
    public Company(String name, String address, String phone, String contact, int discount) {
        super(name, address, phone);
        this.contact = contact;
        this.discount = discount;
    }

    /**
     * Gets the name of the contact person at the company
     * 
     * @return The contact person's name
     */
    public String getContact() {
        return contact;
    }

    /**
     * Gets the discount percentage the company is eligible for
     * 
     * @return The discount percentage
     */
    public int getDiscount() {
        return discount;
    }
    
    /**
     * Returns a string representation of the Company object.
     * The format is: "Customer toString() (contact, discount%)"
     * 
     * @return A string representation of the Company
     */
    @Override
    public String toString() {
        return super.toString() + " (" + contact + ", " + discount + "%) ";
    }
}