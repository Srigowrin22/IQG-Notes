package oe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.PrintWriter;

import java.util.Date;
import java.util.Scanner;

public class OrderEntry {
    public static void main(String[] args) throws Exception {
        Order order = new Order();
        double orderTotal;
        boolean exceedsLimit;
        double taxRate = 0.0825;
        double taxValue;

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
        String shipDate = order.getShipDate(region);
        System.out.println("Order Date:         " + order.getOrderDate());
        System.out.println("Days till shipping: " + 
                           Util.getDaysToShip(region));
        System.out.println("Expected Ship Date: " + shipDate);

        System.out.println("\nCustomers:");
        System.out.println(DataMan.customer1.toString());
        System.out.println(DataMan.customer2.toString());

        order.setCustomer(DataMan.customer3);
        order.showOrder();

        // Order order2 = new Order(new Date(), "overnight");
        Order order2 = new Order(Util.getDate(7, 3, 2002), "overnight");
        order2.setCustomer(DataMan.customer4);
        order2.showOrder();

        //////
        // FileI/O
        String fileName = "customers.txt";
        PrintWriter pw = new PrintWriter(fileName);
        pw.println(DataMan.customer1);
        pw.println(DataMan.customer2);
        pw.println(DataMan.customer3);
        pw.println(DataMan.customer4);
        pw.close();

        System.out.println("Using FileInputStream");
        FileInputStream fis = new FileInputStream(fileName);
        int fileSize = fis.available();
        byte[] bbuf = new byte[fileSize];
        fis.read(bbuf);
        fis.close();
        //System.out.println(bbuf);
        for (int cx = 0; cx < fileSize; cx++) {
            System.out.print(bbuf[cx]);
            // System.out.print((char)bbuf[cx]);
        }
        System.out.println();


        System.out.println("Using InputStreamReader");
        char[] cbuf = new char[fileSize];
        InputStreamReader isr = 
            new InputStreamReader(new FileInputStream(fileName));
        isr.read(cbuf);
        System.out.println(cbuf);

        System.out.println("Using Scanner");
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
        sc.close();

        // OBJECT i/o
        ObjectOutputStream cs = 
            new ObjectOutputStream(new FileOutputStream("customers.ser"));
        cs.writeObject(DataMan.customer1); //entire object is written 
        cs.close();
        ObjectInputStream ois = 
            new ObjectInputStream(new FileInputStream("customers.ser"));
        Customer restCust1 = 
            (Customer)ois.readObject(); //entire object is read 
        ois.close();
        System.out.println("Using stored customer");
        System.out.println(restCust1);

        ObjectOutputStream os = 
            new ObjectOutputStream(new FileOutputStream("order.ser"));
        os.writeObject(order2); //entire object is written 
        os.close();
        ObjectInputStream is = 
            new ObjectInputStream(new FileInputStream("order.ser"));
        Order restOrd2 = (Order)is.readObject(); //entire object is read 
        is.close();
        System.out.println("Using stored Order2");
        restOrd2.showOrder();
    }
}
