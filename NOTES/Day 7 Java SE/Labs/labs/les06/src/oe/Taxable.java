package oe;

/**
 * Interface defining tax calculation behavior for taxable items.
 *
 * <p>This interface provides:
 * <ul>
 *   <li>A standard tax rate constant (10%)</li>
 *   <li>A method contract for calculating tax amounts</li>
 * </ul>
 *
 * <p>Classes implementing this interface must provide their own
 * implementation of the tax calculation logic.
 *
 * <p>Example implementation:
 * <pre>
 * public class TaxableProduct implements Taxable {
 *     public double getTax(double amount) {
 *         return amount * TAX_RATE;
 *     }
 * }
 * </pre>
 *
 * @author [Your Name]
 * @version 1.0
 */

public interface Taxable {
    /**
     * The standard tax rate (10%) applied to taxable items.
     * Value is 0.10 (10%) and is implicitly public, static and final.
     */
    double TAX_RATE = 0.10;

    /**
     * Calculates the tax amount for the given value.
     *
     * @param amount The amount to calculate tax for
     * @return The calculated tax amount
     */
    double getTax(double amount);
}
