/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

/**
 *
 * @author srigowri.n
 */
public class CompositePatternTester {
    public static void main(String[] args) {
        Order tools = new Tools();
        Order equipment = new Equipments();
        Order grocery = new Grocery();
        
        OrderItems orderItemRef = new OrderItems();
        orderItemRef.add(tools);
        orderItemRef.add(equipment);
        orderItemRef.add(grocery);
        orderItemRef.addOrder("Order1");


    }
}
