package oe;

/**
 * Custom exception class indicating that a requested resource was not found.
 * This exception should be thrown when searching for non-existent entities
 * such as customers, products, or other domain objects.
 *
 * <p>Example usage:
 * <pre>
 * if (customer == null) {
 *     throw new NotFoundException("Customer with ID " + id + " not found");
 * }
 * </pre>
 *
 * @author [Your Name]
 * @version 1.0
 * @see Exception
 */
public class NotFoundException extends Exception {
    
    /**
     * Constructs a new NotFoundException with the specified detail message.
     * The message should clearly indicate what resource was not found.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public NotFoundException(String message) {
        super(message);
    }
}