/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lesson03.behavioural.dp.chainofresp;

import java.util.Scanner;

/**
 *
 * @author srigowri.n
 */
public class CORTester {
    private DispenseChain c1;
    
    public CORTester(){
        this.c1 = new Rupee500Dispenser();
        DispenseChain c2 = new Rupee200Dispenser();
        DispenseChain c3 = new Rupee100Dispenser();
        
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }
    
    public static void main(String[] args) {
        CORTester corTestRef = new CORTester();
        
        int amount = 0;
        System.out.println("Enter Withdrawal amount: ");
        java.util.Scanner scan = new java.util.Scanner(System.in);
        amount = scan.nextInt();
        if(amount%100 != 0){
            System.out.println("Amount should be in multiples of 100");
        }else{
            corTestRef.c1.dispense(new Currency(amount));
        }
        return;
    }
}
