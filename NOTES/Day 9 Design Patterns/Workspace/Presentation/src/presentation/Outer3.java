/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
class Outer3 {
    
    public void show(){
        System.out.println("Non final Outer method");
    }
    
    int a = 10;
//    a = 90;
    final void outerMethod() {
        System.out.println("inside outerMethod");

        abstract class Inner {

            void innerMethod() {
                System.out.println("inside innerMethod");
                System.out.println(a); // final variable 
                a = 20;
                show(); // non-final 
            }
        }

        class Inner2 extends Inner{
            
        }
        Inner y = new Inner2();

//        Inner y = new Inner();
        y.innerMethod();
    }
}

class Main {

    public static void main(String[] args) {
        
        Outer3 x = new Outer3();
        x.outerMethod();   
    }
}

/*
    The main reason we had to declare a local variable as a final is that 
    the local variable lives on the stack till the method is on the stack but 
    there might be a case the object of the inner class still lives on the heap. 
        
        If an inner class tries to access a local variable from its enclosing method, 
        and that variable is not final, there is a risk: of stack frame getting destroyed.

*/

/*
    Method local inner class can’t be marked as private, protected, static
    but can be marked as abstract and final, but not both at the same time.
*/

/*
    If you declare a local inner class as abstract, you cannot instantiate it directly 
    (since abstract classes cannot be instantiated). 
    Instead, you must:
        1) Create an anonymous subclass (provide implementation by overriding the methods).
        2) Or, define a concrete subclass inside the same method.
*/