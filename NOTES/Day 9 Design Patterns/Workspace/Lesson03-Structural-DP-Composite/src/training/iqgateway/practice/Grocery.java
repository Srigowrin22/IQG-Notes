/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

/**
 *
 * @author srigowri.n
 */
public class Grocery implements Order {
    
     @Override
    public void addOrder(String name) {
        System.out.println("Adding Grocery to: " + name);
    }  
    
//    @Override
//    public void getTotal(Integer amt){
//        System.out.println("Total Grocery: " + amt);
//    }
}

