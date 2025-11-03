/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.iqgateway;
import java.util.*;

/**
 *
 * @author Sai Baba
 */
public class StackExample {
    
    public static void main(String[] args) {
        
        Stack<Integer> integerStackRef = new Stack<>();
        boolean isEmpty = integerStackRef.empty();
        System.out.println("Is Stack Empty " + isEmpty );
        
        // Lets Add Elements to the Stack
        integerStackRef.push(10);
        integerStackRef.push(20);
        integerStackRef.push(30);
        integerStackRef.push(40);
        integerStackRef.push(50);
        
        // Get the Content From Stack
        System.out.println("Elements Part of the Stack : " + integerStackRef);
        boolean isEmpty1 = integerStackRef.empty();
        System.out.println("Is Stack Empty " + isEmpty1 );
        
        // Removing Elements 
        int removedElement = integerStackRef.pop();
        System.out.println("Elements Part of the Stack : " + integerStackRef);
        System.out.println("Removed Element is : " + removedElement);
     
        int removedElementAtIndex = integerStackRef.remove(2);
        System.out.println("Elements Part of the Stack : " + integerStackRef);
        System.out.println("Removed Element is : " + removedElementAtIndex);
     
        int topElement = integerStackRef.peek();
        System.out.println("Elements Part of the Stack : " + integerStackRef);
        System.out.println("Top Element is : " + topElement);
        
        // Searching
        int position = integerStackRef.search(20);
        System.out.println("Positio of Element is : " + position);
        
        // Iterate The Stack [ Loops Using Enumeration /Iterator ]
        // But We Can Even Use forEach [ Lambdas ]
        integerStackRef.forEach(data -> System.out.println(data));
    }
}
