/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
public class ArrayStoreExceptionExmp { 
  
    public static void main(String args[]) 
    { 
  
        // Since Double class extends Number class 
        // only Double type numbers 
        // can be stored in this array 
        Number[] a = new Double[2]; 
  
        // Trying to store an integer value 
        // in this Double type array 
        a[0] = new Integer(4); 
    } 
} 