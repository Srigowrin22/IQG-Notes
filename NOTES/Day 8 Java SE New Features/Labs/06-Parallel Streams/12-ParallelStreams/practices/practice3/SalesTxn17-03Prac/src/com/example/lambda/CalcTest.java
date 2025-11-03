package com.example.lambda;

import java.util.List;

/**
 *
 * @author oracle
 */
public class CalcTest {
    
    public static void main(String[] args) {

        List<SalesTxn> tList = SalesTxn.createTxnList();
        
        // Print out Transaction Totals
        System.out.println("=== Transactions Totals ===");
       
        double t2 = tList.stream()
                .filter(e -> e.getBuyerName().equals("PriceCo"))
                .mapToDouble(e -> e.getTransactionTotal())   
                .parallel()
                .reduce(0,(sum, e) -> sum + e);
        
        System.out.printf("%nPriceCo Total: $%,9.2f%n", t2);

        int c2 = tList.parallelStream()
                .filter(e -> e.getBuyerName().equals("PriceCo"))
                .mapToInt(e -> 1)   
                .reduce(0,(sum, e) -> sum + e);
        
        System.out.printf("PriceCo Transactions: %,6d%n", c2);
        
    }
}
