/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public interface Subject {
    public void register(Observer obs);
    public void unRegister(Observer obs);
    
    public void notifyObservers();
    
    public Object getUpdate(Observer obs);
    
}
