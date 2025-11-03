/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
// Java Program to Illustrate Static Nested Classes
// Importing required classes
import java.util.*;

class Outer2 {

    private static void outerMethod() {
        System.out.println("inside outer2 class Method");
//        Inner.display();
    }

    public void show() {
        System.out.println("Non static outer method");
    }
    
    static class Inner {

        public static void display() {
            System.out.println("inside inner class Method");

            outerMethod(); // static 

//            show();
        }
    }

}

class Main {

    public static void main(String args[]) {
        Outer2.Inner.display();
    }
}
