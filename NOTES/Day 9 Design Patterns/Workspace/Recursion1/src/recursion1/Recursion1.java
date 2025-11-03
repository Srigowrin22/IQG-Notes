/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package recursion1;

/**
 *
 * @author srigowri.n
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

