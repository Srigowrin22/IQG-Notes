/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.bridge;

/**
 *
 * @author srigowri.n
 */
public class Circle extends Shape{
    public Circle(Color c){
        super(c);
    }
    
    @Override
    public void applyColor() {
        System.out.println("Colored Circle");
    }
}
