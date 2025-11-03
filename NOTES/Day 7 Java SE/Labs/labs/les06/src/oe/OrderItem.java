package oe;

import java.io.Serializable;

/**
 * Represents an item within an order that implements Serializable for object serialization.
 * 
 * <p>This class manages order item information including:
 * <ul>
 *   <li>Line number within the order</li>
 *   <li>Product details</li>
 *   <li>Quantity and unit price</li>
 *   <li>Tax calculation for taxable items</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>
 * Product product = DataMan.findProductByID(123);
 * OrderItem item = new OrderItem(product);
 * item.setQuantity(2);
 * double itemTotal = item.getItemTotal();
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Serializable
 * @see Product
 * @see Taxable
 */
public class OrderItem implements Serializable {
    /**
     * The line number of this item within the order
     */
    private int lineNbr;
    
    /**
     * The quantity of this item
     */
    private int quantity;
    
    /**
     * The unit price of this item
     */
    private double unitPrice;
    
    /**
     * The product associated with this order item
     */
    private Product product;

    /**
     * Constructs a new OrderItem with the specified product.
     * Initializes quantity to 1 and sets unit price from product's retail price.
     * 
     * @param newProduct The product for this order item
     */
    public OrderItem(Product newProduct) {
        product = newProduct;
        unitPrice = product.getRetailPrice();
        quantity = 1;
    }
    
    //    public OrderItem(int productId, double itemPrice){
    //        product = productId;
    //        unitPrice = itemPrice;
    //        quantity = 1;
    //    }

    /**
     * Gets the line number of this item
     * @return The line number
     */
    public int getLineNbr() {
        return lineNbr;
    }

    /**
     * Gets the quantity of this item
     * @return The quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the unit price of this item
     * @return The unit price
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Calculates the total amount for this item (quantity × unit price)
     * @return The calculated item total
     */
    public double getItemTotal() {
        return quantity * unitPrice;
    }

    /**
     * Gets the product associated with this item
     * @return The product object
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the line number for this item
     * @param newLineNbr The new line number
     */
    public void setLineNbr(int newLineNbr) {
        lineNbr = newLineNbr;
    }

    /**
     * Sets the quantity for this item
     * @param newQuantity The new quantity
     */
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    /**
     * Sets the unit price for this item
     * @param newUnitPrice The new unit price
     */
    public void setUnitPrice(double newUnitPrice) {
        unitPrice = newUnitPrice;
    }

    /**
     * Sets the product for this item
     * @param product The new product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Calculates the tax for this item if the product is taxable
     * @return The calculated tax amount, or 0 if product is not taxable
     */
    public double getTax() {
        double itemTax = 0.0;
        if (product instanceof Taxable) {
            itemTax = ((Taxable)product).getTax(getItemTotal());
        }
        return itemTax;
    }

    /**
     * Returns a string representation of this order item
     * @return Formatted string with item details including tax if applicable
     */
    public String toString() {
        
        // return lineNbr + " " + product + " " + quantity + " $" +
        // getItemTotal();
        
        if (product instanceof Taxable)
            return lineNbr + " " + product + " " + quantity + " Tax: " +
                Util.toMoney(getTax());
        else
            return lineNbr + " " + product + " " + quantity + " " +
                Util.toMoney(getItemTotal());
    }
}
