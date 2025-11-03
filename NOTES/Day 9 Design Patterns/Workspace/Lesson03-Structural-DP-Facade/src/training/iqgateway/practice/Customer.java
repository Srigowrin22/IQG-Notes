/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

/**
 *
 * @author srigowri.n
 */
public class Customer {
    public static void main(String[] args) {
        OrderFacade order = new OrderFacade();
        order.orderItems("Perfume", 1);
        order.payBill("ARJ01", "Perfume", 1, 250.89);
        order.packItem();
        order.deliver();
    }
}
