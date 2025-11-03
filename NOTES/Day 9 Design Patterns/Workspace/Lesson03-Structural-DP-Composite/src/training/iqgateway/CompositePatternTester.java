/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class CompositePatternTester {
    public static void main(String[] args) {
        
        Shape triangleShape1 = new Triangle();
        Shape triangleShape2 = new Triangle();
        
        Shape circleShape1 = new Circle();
        Shape circleShape2 = new Circle();
        
        Drawing drawingRef = new Drawing();
        drawingRef.add(triangleShape1);
        drawingRef.add(triangleShape2);
        drawingRef.add(circleShape1);
        drawingRef.add(circleShape2);
        drawingRef.draw("Blue");
        drawingRef.clear();
        
        drawingRef.add(triangleShape1);
        drawingRef.add(triangleShape2);
        drawingRef.add(circleShape1);
        drawingRef.add(circleShape2);
        drawingRef.draw("Green");
        drawingRef.clear();
        

    }
}
