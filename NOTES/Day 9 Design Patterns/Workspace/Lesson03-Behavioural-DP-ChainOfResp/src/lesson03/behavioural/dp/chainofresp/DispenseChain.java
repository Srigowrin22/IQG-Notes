/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lesson03.behavioural.dp.chainofresp;

/**
 *
 * @author srigowri.n
 */
public interface DispenseChain {
    
    public void setNextChain(DispenseChain nextChain);
    
    public void dispense(Currency cur);
}
