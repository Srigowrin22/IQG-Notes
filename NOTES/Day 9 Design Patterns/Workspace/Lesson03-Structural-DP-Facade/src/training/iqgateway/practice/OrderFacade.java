/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.practice;

/**
 *
 * @author srigowri.n
 */
public class OrderFacade {
    private WareHouse wareHouse;
    private PaymentProcess paymentProcess;
    private Packaging packaging;
    private Deliver deliver;

    public OrderFacade() {
        this.wareHouse = new WareHouse();
        this.paymentProcess = new PaymentProcess();
        this.packaging = new Packaging();
        this.deliver = new Deliver();
    }
    
    public void orderItems(String orderName, int number){
        wareHouse.service();
        wareHouse.checkAvailability(orderName, number);
    }
    
    public void payBill(String accID, String orderName, int number, double payAmt){
        paymentProcess.service();
        paymentProcess.payBill(accID, orderName, number, payAmt);
    }
    
    public void packItem(){
        packaging.service();
        packaging.packOrderItem();
    }
    
    public void deliver(){
        deliver.service();
        deliver.deliverOrder();
    }
}
