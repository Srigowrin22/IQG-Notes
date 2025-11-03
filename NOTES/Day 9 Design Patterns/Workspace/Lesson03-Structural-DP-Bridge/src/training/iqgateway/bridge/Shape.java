/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package training.iqgateway.bridge;

/**
 *
 * @author srigowri.n
 */
public abstract class Shape {

    protected Color color;
    
    public Shape(Color c){
        this.color = c;
    }
    
    public abstract void applyColor();
}
