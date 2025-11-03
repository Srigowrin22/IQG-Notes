/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author srigowri.n
 */
public class OrderItems implements Order{
    private List<Order> orderItem = new ArrayList<>();

    @Override
    public void addOrder(String name) {
        for(Order o: orderItem){
            o.addOrder(name);
        }
    }
    
//    @Override
//    public void getTotal(Integer amt){
//        for(Order o: orderItem){
//            o.addOrder(amt);
//        }
//    }
    
    public void add(Order order){
        orderItem.add(order);
    }
    
    public void remove(Order order){
        orderItem.remove(order);
    }
    
    public void clear(){
        orderItem.clear();
    }
}
