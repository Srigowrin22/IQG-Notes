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
public class MyTopic implements Subject{
    
    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();
    
    public MyTopic(){
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer obs) {
        if(obs == null){
            throw new NullPointerException("Null Observer");
        }
        synchronized(MUTEX){
            if(!observers.contains(obs)){
                observers.add(obs);
            }
        }
    }

    @Override
    public void unRegister(Observer obs) {
        synchronized(MUTEX){
            observers.remove(obs);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observerLocal = null;
        synchronized(MUTEX){
            if(!changed){
                return;
            }
            observerLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for(Observer obj: observerLocal){
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer obs) {
       return this.message;
    } 

    public void postMessage(String msg){
        System.out.println("Message Posted to Topic : "+msg);
            this.message=msg;
            this.changed=true;
            notifyObservers();
    }
}
