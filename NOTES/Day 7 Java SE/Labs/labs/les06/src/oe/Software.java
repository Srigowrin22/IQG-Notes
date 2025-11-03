package oe;

/**
 * Represents a software product that extends the base Product class.
 * Software products have a default license period and specific pricing.
 * 
 * <p>This class inherits all basic product properties from Product
 * and adds software-specific license information.
 *
 * <p>Example usage:
 * <pre>
 * Software app = new Software("Text Editor", "Basic text editing software", 49.99);
 * System.out.println(app.getName() + " license: " + app.licence);
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Product
 */
public class Software extends Product {
    
    /**
     * The license terms for this software product.
     * Defaults to "30 Day Trial" if not specified otherwise.
     */
    String licence = "30 Day Trial";
    
    /**
     * Constructs a new Software product with specified details.
     * 
     * @param name The name of the software product
     * @param desc The description of the software
     * @param price The retail price of the software
     */
    public Software(String name, String desc, double price) {
        setName(name);
        setDescription(desc);
        setRetailPrice(price);
    }
}