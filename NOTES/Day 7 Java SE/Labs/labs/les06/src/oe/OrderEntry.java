package oe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.Scanner;

/**
 * Main class for the Order Entry system that handles order processing operations.
 * 
 * <p>This class provides functionality for:
 * <ul>
 *   <li>Creating and managing customer orders</li>
 *   <li>Serializing/deserializing order data</li>
 *   <li>Reading input and writing output</li>
 * </ul>
 *
 * <p>The system allows for order creation, modification, and persistence
 * through file operations.
 * 
 * <p>Example usage:
 * <pre>
 * // Run the order entry system
 * java oe.OrderEntry
 * </pre>
 * 
 * @author [Your Name]
 * @version 1.0
 * @see Order
 * @see Customer
 * @see Product
 */    
public class OrderEntry {
    
    /**
     * Main entry point for the Order Entry system.
     * 
     * <p>Handles the primary workflow including:
     * <ul>
     *   <li>Order creation</li>
     *   <li>Customer selection</li>
     *   <li>Product selection</li>
     *   <li>Order serialization</li>
     * </ul>
     * 
     * @param args Command line arguments (not currently used)
     * @throws Exception if any critical system error occurs during execution
     */
    public static void main(String[] args) throws Exception {
        Order order = new Order();

        double orderTotal;
        boolean exceedsLimit;
        double taxRate = 0.0825;
        double taxValue;

        System.out.println("*******************************************************************");
        System.out.println("Order Entry Application");
        orderTotal = order.getOrderTotal();
        System.out.println("Order Total: " + orderTotal);

        taxValue = orderTotal * taxRate;
        System.out.println("Tax Value:   " + taxValue);

        orderTotal += taxValue;
        System.out.println("Final Total: " + orderTotal);

        exceedsLimit = orderTotal > 10.00;
        System.out.println("Total Exceeds $10.00: " + exceedsLimit);

        // order.setOrderDate(27, 1, 2001);
        order.setOrderDate(27, 2, 2001);
        char region = 'A';
        String shipDate;
        shipDate = order.getShipDate(region);
        System.out.println("Order Date:         " + order.getOrderDate());
        System.out.println("Days till shipping: " +
                           Util.getDaysToShip(region));
        System.out.println("Expected Ship Date: " + shipDate);
        System.out.println("*******************************************************************");

        //        Customer customer1 = new Customer();
        //        Customer customer2 = new Customer();
        //
        //                customer1.setId(1);
        //                customer1.setName("Gary Williams");
        //                customer1.setAddress("Houston, TX");
        //                customer1.setPhone("731.123.4323");
        //
        //                customer2.setId(2);
        //                customer2.setName("Lynn Munsinger");
        //                customer2.setAddress("Oriando, FL");
        //                customer2.setPhone("407.323.7566");
        //
        //                System.out.println("Customers:");
        //                System.out.println(customer1.toString());
        //                System.out.println(customer2.toString());


        order.setCustomer(DataMan.customers[0]); // customer
        order.showOrder();

        //                order.setCustomer(DataMan.customer2);
        //                order.showOrder();
        //
        //                Order order5 = new Order();
        //                order5.setCustomer(DataMan.customer3); // customer
        //                order5.showOrder();

        Order order2 = new Order(Util.getDate(7, 3, 2002), "Overnight");
        order2.setCustomer(DataMan.customers[1]); // customer
        order2.showOrder();

        Order order3 = new Order();
        order3.setCustomer(DataMan.findCustomerByID(3)); // company
        order3.showOrder();

        Order order4 = new Order();
        order4.setCustomer(DataMan.customers[3]); // company
        order4.addOrderItem(2000);
        order4.addOrderItem(2002);
        order4.addOrderItem(2001);
        order4.addOrderItem(2002);
        order4.showOrder();

        try {
            Order order5 = new Order();
            order5.setCustomer(DataMan.findCustomerByID(5)); // Individual
            order5.addOrderItem(2002);
            order5.addOrderItem(2004);
            order5.addOrderItem(2004);
            order5.showOrder();

            Order order6 = new Order();
            order6.setCustomer(DataMan.findCustomerByID(7));
            order6.addOrderItem(2001);
            order6.showOrder();
        } catch (NotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("\nProduct is: " +
                               DataMan.findProductByID(2002));
            System.out.println("\nProduct is: " +
                               DataMan.findProductByID(2008));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            java.util.Scanner scan = new java.util.Scanner(System.in);
            System.out.println("Enter the Customer id: ");
            int custId = scan.nextInt();
            Order order7 = new Order();
            order7.setCustomer(DataMan.selectCustomerByID(custId));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        DataMan.closeConnection();

        //-----------------------------------------------------------------------------------------------------------------

        System.out.println("\n_____________________________________________________________________\n");
        String filename =
            "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Labs\\labs\\les06\\src\\oe\\Customers.txt";
        PrintWriter pw = new PrintWriter(filename);


        pw.println(DataMan.findCustomerByID(1));
        pw.println(DataMan.customers[1]);
        pw.println(DataMan.customers[2]);
        pw.println(DataMan.customers[3]);

        pw.close();

        //-----------------------------------------------------------------------------------------------------------------
        // Read using FileInputStream
        FileInputStream fis = new FileInputStream(filename);
        int fileSize = fis.available();
        byte[] bbuf = new byte[fileSize];

        fis.close();

        //        for(int cx = 0; cx < fileSize; cx++){
        //            System.out.println(bbuf[cx]);
        //        }

        // Read by Converting to character and read from the destination file
        InputStreamReader isr =
            new InputStreamReader(new FileInputStream(filename));
        char[] cbuf = new char[fileSize];

        isr.read(cbuf);
        System.out.println(cbuf);
        isr.close();

        // Using Scanner to read and print
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
        sc.close();

        //Serialization
        String fileName =
            "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Labs\\labs\\les06\\src\\oe\\Customers.ser";
        ObjectOutputStream cs =
            new ObjectOutputStream(new FileOutputStream(fileName));
        cs.writeObject(DataMan.customers[1]);
        cs.close();

        ObjectInputStream ois =
            new ObjectInputStream(new FileInputStream(fileName));
        Customer restCust1 = (Customer)ois.readObject();
        ois.close();

        // Use serialization to store and Desirealization to restore an order object
        String fileOrder =
            "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\NOTES\\Day 7 Java SE\\Labs\\labs\\les06\\src\\oe\\Orders.ser";
        ObjectOutputStream oos =
            new ObjectOutputStream(new FileOutputStream(fileOrder));
        oos.writeObject(order2);
        oos.close();

        ObjectInputStream oiss =
            new ObjectInputStream(new FileInputStream(fileOrder));
        Order restOrd2 = (Order)oiss.readObject();
        oiss.close();

        restOrd2.showOrder();

        //-----------------------------------------------------------------------------------------------------------------

    }
}
