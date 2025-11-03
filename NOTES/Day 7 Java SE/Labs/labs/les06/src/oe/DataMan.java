package oe;

import java.util.HashMap;

import java.sql.*;

/**
 * Provides data management functionality for customer and product information.
 * This class serves as a data access layer with both in-memory and database
 * capabilities.
 *
 * <p>The class maintains:
 * <ul>
 *   <li>An in-memory array of Customer objects</li>
 *   <li>A ProductMap (HashMap) of Product objects</li>
 *   <li>Database connection capabilities for customer data</li>
 * </ul>
 *
 * <p>Static initialization blocks ensure data is loaded when the class is first accessed.
 *
 * @author [Your Name]
 * @version 1.0
 * @see Customer
 * @see Company
 * @see Individual
 * @see Product
 * @see ProductMap
 */
public class DataMan {

    /**
     * Database username for connection
     */
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";

    /**
     * Database username for connection
     */
    static String username = "oe";

    /**
     * Database password for connection
     */
    static String pass = "oe";

    /**
     * Database connection object
     */
    private static Connection conn = null;

    /**
     * Array of Customer objects maintained in memory
     */
    public static Customer[] customers = null;

    /**
     * Map of Product objects maintained in memory
     */
    private static ProductMap products = null;

    static {
        buildCustomers();
        buildProducts();
    }

    //    static Customer customer1 =
    //        new Customer("Gary Williams", "Houston, TX", "713.555.8765");
    //
    //    static Customer customer2 =
    //        new Customer("Lynn Munsinger", "Orlando, FL", "407.695.2210");
    //
    //    static Customer customer3 =
    //        new Customer("Rachael O'leary", "Brisbane, QLD", "07.3031.1100");
    //
    //    static Customer customer4 =
    //        new Customer("Tony Obermeit", "Brisbane, QLD", "07.3031.9987");
    //
    //    static Company customer5 =
    //        new Company("Oracle", "Redw...", "80...", "Larry...", 20);
    //
    //    static Individual customer6 =
    //        new Individual("Robert", "Canada, QLD", "90.3031.9987", "50LIC");


    /**
     * Builds the in-memory customer array if not already initialized.
     * Creates 6 sample customers including both regular customers and
     * specialized types (Company and Individual).
     */
    public static void buildCustomers() {
        if (customers != null) {
            return;
        } else {
            customers = new Customer[6];

            customers[0] =
                    new Customer("Gary Williams", "Houston, TX", "713.555.8765");
            customers[1] =
                    new Customer("Lynn Munsinger", "Orlando, FL", "407.695.2210");
            customers[2] =
                    new Customer("Rachael O'leary", "Brisbane, QLD", "07.3031.1100");
            customers[3] =
                    new Customer("Tony Obermeit", "Brisbane, QLD", "07.3031.9987");
            customers[4] =
                    new Company("Oracle", "Redw...", "80...", "Larry...", 20);
            customers[5] =
                    new Individual("Robert", "Canada, QLD", "90.3031.9987",
                                   "50LIC");
        }
    }

    /**
     * Finds a customer by ID in the in-memory array.
     *
     * @param custId The customer ID to search for
     * @return The found Customer object
     * @throws NotFoundException if no customer with the specified ID exists
     */
    public static Customer findCustomerByID(int custId) throws NotFoundException {
        // Customer c = null;
        for (Customer customer : customers) {
            if (customer != null && customer.getId() == custId) {
                // c = customer;
                return customer;
            }
        }
        // return null;
        throw new NotFoundException("Customer with the id " + custId +
                                    " does not exist :) ");
    }

    /**
     * Inner class extending HashMap to manage Product objects.
     * Provides type-safe addition of Product objects using product ID as key.
     */
    private static class ProductMap extends HashMap {

        /**
         * Adds a Product to the map using its ID as the key.
         *
         * @param p The Product to add
         */
        public void add(Product p) {
            String key = Integer.toString(p.getId());
            put(key, p);
        }
    }


    /**
     * Builds the in-memory product map if not already initialized.
     * Creates sample products including Hardware, Software, and Manual types.
     */
    public static void buildProducts() {
        if (products != null) {
            System.out.println("Inside IF");
            return;

        } else {
            System.out.println("Inside Else of Build Products");
            products = new ProductMap();
            products.add(new Hardware("SDRAM - 128 MB", null, 299.0));
            products.add(new Hardware("GP 800x600", null, 48.0));
            products.add(new Software("Spreadsheet - SSP/v2.0", null, 45.0));
            products.add(new Software("Word processing - SWP/V4.5", null,
                                      65.0));
            products.add(new Manual("Manual - Vision OS/2x +", null, 125.0));
        }

    }


    /**
     * Finds a product by ID in the in-memory product map.
     *
     * @param id The product ID to search for
     * @return The found Product object
     * @throws NotFoundException if no product with the specified ID exists
     */
    public static Product findProductByID(int id) throws NotFoundException {
        String key = Integer.toString(id);
        Product product = (Product)products.get(key);
        if (product == null) {
            throw new NotFoundException("Product with the id " + id +
                                        " does not exist :) ");
        }
        return product;
    }

    /**
     * Retrieves a customer from the database by ID.
     *
     * @param id The customer ID to search for in the database
     * @return The found Customer object populated with database data
     * @throws Exception if there are database connection/query issues
     * @throws NotFoundException if no customer with the specified ID exists in the database
     */
    public static Customer selectCustomerByID(int id) throws Exception {

        Customer customer = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, username, pass);
        Statement stmt = conn.createStatement();
        System.out.println("Table Customers query for customer with id: " +
                           id);
        String query =
            ("SELECT cust_last_name, nls_territory" + " FROM customers WHERE customer_id = " +
             id);

        ResultSet res = stmt.executeQuery(query);
        if (res.next()) {
            customer = new Customer();
            customer.setId(id);
            customer.setName(res.getString(1));
            customer.setAddress(res.getString(2));
            System.out.println("Customer found: " + customer);
        } else {
            throw new NotFoundException("Customer with id " + id +
                                        " not found!!! ");
        }
        res.close();
        stmt.close();
        return customer;
    }

    /**
     * Closes the database connection if it exists.
     * Silently handles any exceptions that occur during closure.
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}