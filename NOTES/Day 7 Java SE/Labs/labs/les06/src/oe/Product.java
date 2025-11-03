package oe;

/**
 * Abstract base class representing a product in the system.
 * Provides common product attributes and functionality that all product types share.
 * 
 * <p>This class maintains:
 * <ul>
 *   <li>Auto-generated product IDs</li>
 *   <li>Product name and description</li>
 *   <li>Retail price information</li>
 *   <li>Basic product display functionality</li>
 * </ul>
 *
 * <p>Concrete product classes should extend this class and implement
 * any product-type specific behavior.
 *
 * <p>Example usage:
 * <pre>
 * Product p = new Hardware("Memory", "8GB RAM", 89.99);
 * System.out.println(p.getName() + " costs " + p.getRetailPrice());
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Hardware
 * @see Software
 * @see Manual
 */
public abstract class Product {
    /**
     * Static counter for generating unique product IDs
     */
    private static int nextProductId = 2000;
    
    /**
     * Unique identifier for this product
     */
    private int id;
    
    /**
     * Name of the product
     */
    private String name;
    
    /**
     * Description of the product
     */
    private String description;
    
    /**
     * Retail price of the product
     */
    private double retailPrice;

    /**
     * Default constructor that initializes a new product with:
     * - Auto-generated ID
     * - Default values for other fields
     */
    public Product() {
        id = nextProductId++;
    }

    /**
     * Sets the next product ID to be used for auto-generation
     * @param nextProductId The next ID value to use
     */
    public static void setNextProductId(int nextProductId) {
        Product.nextProductId = nextProductId;
    }

    /**
     * Gets the next product ID that will be assigned
     * @return The next available product ID
     */
    public static int getNextProductId() {
        return nextProductId;
    }

    /**
     * Sets the product ID (overrides auto-generated ID)
     * @param id The ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the product ID
     * @return The product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the product name
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product name
     * @return The product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product description
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the product description
     * @return The product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the retail price
     * @param retailPrice The price to set
     */
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * Gets the retail price
     * @return The retail price
     */
    public double getRetailPrice() {
        return retailPrice;
    }
    
    /**
     * Returns a string representation of the product
     * @return Formatted string containing product details
     */
    @Override
    public String toString() {
        return "[" + Util.getClassName(this) + "]" + id + " " + name + " " +
            Util.toMoney(retailPrice);
    }
}