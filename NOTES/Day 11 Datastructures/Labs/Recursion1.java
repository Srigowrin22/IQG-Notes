/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.iqgateway;

/**
 *
 * @author Sai Baba
 */
public class Recursion1 {
    
    // Direct Recursive Function 
    static void callMe() {
        System.out.println("Call Me ... ");
        callMe(); 
    }
    
    public static void main(String[] args) {
        callMe();
    }
    
}
