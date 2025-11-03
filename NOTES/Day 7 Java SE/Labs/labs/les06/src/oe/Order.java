package oe;

import java.io.Serializable;

import java.util.Date;
import java.util.Calendar;
// 3b
import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents an order in the system that implements Serializable for object serialization.
 *
 * <p>This class manages order information including:
 * <ul>
 *   <li>Order ID (auto-generated)</li>
 *   <li>Order date and shipping details</li>
 *   <li>Customer information</li>
 *   <li>Order items (stored in ArrayList)</li>
 *   <li>Order total and status</li>
 * </ul>
 *
 * <p>Example usage:
 * <pre>
 * Order order = new Order();
 * order.setCustomer(customer);
 * order.addOrderItem(productId);
 * order.showOrder();
 * </pre>
 *
 * @author [Your Name]
 * @version 1.0
 * @see Serializable
 * @see Customer
 * @see OrderItem
 */
public class Order implements Serializable {
    /**
     * Static counter for generating unique order IDs
     */
    private static int nextOrderId = 100;

    /**
     * Unique identifier for this order
     */
    private int id;

    /**
     * Date when the order was placed
     */
    private Date orderDate;

    /**
     * Shipping mode for this order
     */
    private String shipMode;

    /**
     * Total amount for this order
     */
    private double orderTotal;

    /**
     * Current status of the order
     */
    private String status;

    /**
     * Customer who placed this order
     */
    private Customer customer;

    /**
     * List of order items in this order
     */
    ArrayList items;

    //    private OrderItem item1 = new OrderItem();
    //    private OrderItem item2 = new OrderItem();

    /**
     * Default constructor that initializes a new order with:
     * - Auto-generated ID
     * - Current date as order date
     * - Empty items list
     * - Zero order total
     */
    public Order() {
        id = nextOrderId++;
        orderTotal = 0.0;
        orderDate = new Date();
        items = new ArrayList(10);
    }

    /**
     * Constructs a new order with specified date and shipping mode
     *
     * @param newOrderDate The date when the order was placed
     * @param newShipMode The shipping mode for this order
     */
    public Order(Date newOrderDate, String newShipMode) {
        this();
        orderDate = newOrderDate;
        shipMode = newShipMode;
    }

    /**
     * Gets the order ID
     * @return The order ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the order date
     * @return The order date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Calculates and returns the estimated ship date based on region
     *
     * @param region The region code for shipping calculation
     * @return The estimated ship date as a String
     */
    public String getShipDate(char region) {
        int daysToShip = Util.getDaysToShip(region);
        Calendar c = Calendar.getInstance();
        c.setTime(orderDate);
        c.setTime(orderDate);
        c.add(Calendar.DAY_OF_MONTH, daysToShip);
        return c.getTime().toString();
    }

    /**
     * Gets the shipping mode
     * @return The shipping mode
     */
    public String getShipMode() {
        return shipMode;
    }

    /**
     * Gets the order total amount
     * @return The order total amount
     */
    public double getOrderTotal() {

        //        double item1Total, item2Total;
        //
        //        item1.setLineNbr(1);
        //        item1.setQuantity(2);
        //        item1.setUnitPrice(2.95);
        //
        //        item2.setLineNbr(2);
        //        item2.setQuantity(2);
        //        item2.setUnitPrice(3.50);
        //
        //        item1Total = item1.getItemTotal();
        //        item2Total = item2.getItemTotal();

        // System.out.println("Item 1 Total: " + item1Total);
        // System.out.println("Item 2 Total: " + item2Total);

        //        orderTotal = item1Total + item2Total;
        return orderTotal;
    }

    /**
     * Gets the order status
     * @return The order status
     */
    public String getstatus() {
        return status;
    }

    /**
     * Sets the order ID
     * @param newId The new order ID
     */
    public void setId(int newId) {
        id = newId;
    }

    /**
     * Sets the order date using day, month, and year values
     *
     * @param d Day of month (1-31)
     * @param m Month (1-12)
     * @param y Year
     */
    public void setOrderDate(int d, int m, int y) {
        int daysInMonth = Util.lastDayInMonth(m, y);

        if (d > 0 && d <= daysInMonth) {
            if ((m > 0 && m <= 12) && (y > 0)) {
                Calendar c = Calendar.getInstance();
                c.set(y, m - 1, d);
                orderDate = c.getTime();
            }
        }
    }

    /**
     * Sets the order date using a Date object
     * @param newOrderDate The new order date
     */
    public void setOrderDate(Date newOrderDate) {
        orderDate = newOrderDate;
    }

    /**
     * Sets the shipping mode
     * @param newShipMode The new shipping mode
     */
    public void setShipMode(String newShipMode) {
        shipMode = newShipMode;
    }

    /**
     * Sets the order total amount
     * @param newOrderTotal The new order total amount
     */
    public void setOrderTotal(double newOrderTotal) {
        orderTotal = newOrderTotal;
    }

    /**
     * Sets the order status
     * @param newStatus The new order status
     */
    public void setStatus(String newStatus) {
        status = newStatus;
    }

    /**
     * Gets the customer who placed this order
     * @return The customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer for this order
     * @param newCustomer The customer object
     */
    public void setCustomer(Customer newCustomer) {
        customer = newCustomer;
    }

    /**
     * Adds an order item to this order
     *
     * @param productId The ID of the product to add
     */
    public void addOrderItem(int productId) {
        OrderItem item = null;
        boolean productFound = false;
        for (int i = 0; i < items.size() && !productFound; i++) {
            item = (OrderItem)items.get(i);
            productFound = (item.getProduct().getId() == productId);
        }

        if (productFound) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            // item = new OrderItem(product, 5.0);
            try {
                Product p = DataMan.findProductByID(productId);
                if (p != null) {
                    item = new OrderItem(p);
                    items.add(item);
                }
                // items.add(item);
                item.setLineNbr(items.size());
            } catch (NotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
                return;
            }
            orderTotal += item.getUnitPrice();
        }
    }

    /**
     * Returns a string representation of the order
     * @return Formatted string with order details
     */
    public String toString() {

        return "Order: " + id + " Date: " + Util.toDateString(orderDate) +
            " Shipped: " + shipMode + " (" + Util.toMoney(getOrderTotal()) +
            ")";

        //        Object[] msgVals =
        //        { new Integer(id), Util.toDateString(orderDate), shipMode,
        //          Util.toMoney(getOrderTotal()) };
        //        return MessageFormat.format("Order: {0} Date: {1} Shipped: {2} (Total: {3})",
        //                                    msgVals);

        //        return "**TO STRING**";
    }

    /**
     * Displays the complete order details including:
     * - Order information
     * - Customer details
     * - All order items
     * - Tax and total calculations
     */
    public void showOrder() {
        double taxTotal = 0.0;
        System.out.println(toString());
        if (customer != null) {
            System.out.println("Customer: " + customer);
        }
        System.out.println("Items:");

        //        if (item1 != null) {
        //            System.out.println(item1.toString());
        //        }
        //        if (item2 != null) {
        //            System.out.println(item2);
        //        }

        for (Iterator it = items.iterator(); it.hasNext(); ) {
            OrderItem item = (OrderItem)it.next();
            taxTotal += item.getTax();
            System.out.println(item.toString());
        }
        System.out.println("Total tax: " + Util.toMoney(taxTotal));
        System.out.println("Order Total + Tax Total " +
                           Util.toMoney(orderTotal + taxTotal));
        System.out.println();
        System.out.println("_____________________________________________________________________");
    }
}
