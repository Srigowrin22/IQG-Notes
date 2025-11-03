/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.bridge;

/**
 *
 * @author srigowri.n
 */
public class BridgePatternTester {
    
    public static void main(String[] arg){
        Shape triangleRef = new Triangle(new RedColor());
        triangleRef.applyColor();
        
        Shape circleRef = new Circle(new BlueColor());
        circleRef.applyColor();
        
    }
}
