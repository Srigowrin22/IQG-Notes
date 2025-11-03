package com.example.lambda;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *
 * @author oracle
 */
public class CalcTest {
    
    public static void main(String[] args) {

        List<SalesTxn> tList = SalesTxn.createTxnList();
        
        // Print out Transaction Totals
        System.out.println("=== Transactions Totals ===");
        double t1 = tList.parallelStream()
                .filter(e -> e.getBuyerName().equals("Radio Hut"))
                .mapToDouble(e -> e.getTransactionTotal())      
                .sum();
        
        System.out.printf("Radio Hut Total: $%,9.2f%n", t1);
        
        double t2 = tList.parallelStream()
                .filter(e -> e.getBuyerName().equals("PriceCo"))
                .mapToDouble(e -> e.getTransactionTotal())      
                .sum();
        
        System.out.printf("PriceCo Total: $%,9.2f%n", t2);
        
        double t3 = tList.parallelStream()
                .filter(e -> e.getBuyerName().equals("Best Deals"))
                .mapToDouble(e -> e.getTransactionTotal())      
                .sum();
        
        System.out.printf("Best Deals Total: $%,9.2f%n", t3);                 
        
    }
}
