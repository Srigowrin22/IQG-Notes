/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
// Java Program to Demonstrate Nested class 
class Outer1 {
    
    public void outerShow(){
        System.out.println("Outer Class method");
//        show();

        Inner in = new Inner();
        in.show();

//        Inner.show();
    }
    
    class Inner {

        public void show() {
            System.out.println("In a nested class method");
            outerShow();
        }
    }
}

class Main {

    public static void main(String[] args) {
                
        Outer1.Inner in = new Outer1().new Inner();
        in.show();

//        Outer1 member = new Outer1();
//        Outer1.Inner in2 = member.new Inner();
//        in2.show();
//
//        Outer1 out = new Outer1();
//        out.outerShow();

    }
}


/* Inner class is implicitly associated with the object of its outer class and hence 
   the inner class couldn't define a static method.

   But Since JAVA Version 16 we can have static members in the inner class.
*/