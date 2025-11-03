package oe;

/**
 * Represents a manual product that extends the base Product class.
 * Manuals have a default publisher and contain product documentation.
 *
 * <p>Example usage:
 * <pre>
 * Manual userGuide = new Manual("User Guide", "Product documentation", 29.99);
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Product
 */
public class Manual extends Product {
    
    /**
     * The publisher of this manual. Defaults to "Oradev Press".
     */
    String publisher = "Oradev Press";
    
    /**
     * Constructs a new Manual product with the specified details.
     * 
     * @param name The name/title of the manual
     * @param desc The description of the manual's contents
     * @param price The retail price of the manual
     */
    public Manual(String name, String desc, double price) {
        setName(name);
        setDescription(desc);
        setRetailPrice(price);
    }
}