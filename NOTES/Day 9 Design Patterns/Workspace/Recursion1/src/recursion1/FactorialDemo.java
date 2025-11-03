/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursion1;

/**
 *
 * @author srigowri.n
 */
public class FactorialDemo {
    
    static int factorial(int n) {
        if( n == 1) {
            return 1;
        }
        else {
            return (n * factorial(n-1));
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Factorial  of 5 is " + factorial(5));
    }
}
