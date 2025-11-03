package oe;

/**
 * Represents a hardware product that extends the base Product class
 * and implements the Taxable interface.
 *
 * <p>Hardware products have a default warranty period of 6 months
 * and are subject to taxation based on the TAX_RATE defined in
 * the Taxable interface.
 *
 * <p>Example usage:
 * <pre>
 * Hardware ram = new Hardware("SDRAM - 128 MB", "Memory module", 299.0);
 * double tax = ram.getTax(ram.getRetailPrice());
 * </pre>
 *
 * @author [Your Name]
 * @version 1.0
 * @see Product
 * @see Taxable
 */
public class Hardware extends Product implements Taxable {

    /**
     * The warranty period in months for this hardware product.
     * Defaults to 6 months if not specified otherwise.
     */
    int warrantyPeriod = 6;

    /**
     * Constructs a new Hardware product with the specified details.
     *
     * @param name The name of the hardware product
     * @param desc The description of the hardware product
     * @param price The retail price of the hardware product
     */
    public Hardware(String name, String desc, double price) {
        setName(name);
        setDescription(desc);
        setRetailPrice(price);
    }

    /**
     * Calculates the tax amount for this hardware product
     * using the TAX_RATE defined in the Taxable interface.
     *
     * @param amount The amount to calculate tax for (typically the retail price)
     * @return The calculated tax amount
     */
    @Override
    public double getTax(double amount) {
        return amount * TAX_RATE;
    }
}
