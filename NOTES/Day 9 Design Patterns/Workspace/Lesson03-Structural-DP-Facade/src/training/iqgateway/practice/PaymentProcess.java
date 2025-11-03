/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

/**
 *
 * @author srigowri.n
 */
public class PaymentProcess implements Services{
    
     public void service(){
        System.out.println("Payment pending!");
    }
    public void payBill(String accID, String orderName, int number, double amount) {
        System.out.println("Paying bill from account " + accID + " With orderName "  + orderName + " of the quantity " + number + " with the amount " + amount);
        System.out.println("Payment done! ");
    }
}
