/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.abstractfactorymethod;

/**
 *
 * @author srigowri.n
 */
public class ModernChair implements Chair{

    @Override
    public boolean hasLeg() {
        return false;
    }

    @Override
    public void sitON() {
        System.out.print("Feels Modern and Looks Like Current Trend!! ");
    }
    
}
