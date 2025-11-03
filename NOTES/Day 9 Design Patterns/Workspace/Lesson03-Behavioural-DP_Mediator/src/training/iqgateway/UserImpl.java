/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class UserImpl extends User{
    
    public UserImpl(ChatMediator med, String name){
        super(med, name);
    }
    
    @Override
    public void send(String msg){
        System.out.println(this.name + " : Sending Message = " + msg);
        mediator.sendMessage(msg, this);
    }
    
    @Override
    public void receive(String msg){
        System.out.println(this.name + " : Received Message = " + msg);
    }
}
