/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

/**
 *
 * @author srigowri.n
 */
public class WareHouse implements Services{
    public void service(){
        System.out.println("Checking in warehouse!");
    }
    public void checkAvailability(String orderName, int number){
        if(!orderName.equals(null) && number>0){
            System.out.println(orderName + "Item available!");
        }else{
            System.out.println("Item out of stock");
        }
    }
}

