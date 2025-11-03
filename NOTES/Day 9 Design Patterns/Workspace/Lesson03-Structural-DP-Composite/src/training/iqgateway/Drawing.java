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
public class Drawing implements Shape{

    private List<Shape> shapes = new ArrayList<>();
    
    @Override
    public void draw(String fillColor) {
        for(Shape s: shapes){
            s.draw(fillColor);
        }
    }
    
    public void add(Shape s){
        shapes.add(s);
    }
    
    public void remove(Shape s){
        shapes.remove(s);
    }
    
    public void clear(){
        shapes.clear();
    }
}
