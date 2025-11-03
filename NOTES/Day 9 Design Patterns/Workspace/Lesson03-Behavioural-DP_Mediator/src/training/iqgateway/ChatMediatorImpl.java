/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author srigowri.n
 */
public class ChatMediatorImpl implements ChatMediator{
    
    private List<User> users;
    public ChatMediatorImpl(){
        this.users = new ArrayList();
    }
    
    @Override
    public void addUser(User user){
        this.users.add(user);
    }
    
    @Override
    public void sendMessage(String msg, User user){
        for(User u: this.users){
            if(u!=user){
                u.receive(msg);
            }
        }
    }
}
