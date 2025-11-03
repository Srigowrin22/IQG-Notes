/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
class Outer4 {
    void outerMethod() {
        
        abstract class LocalInner { 
            abstract void show();
        }

        LocalInner inner = new LocalInner() {  
            @Override
            void show() {
                System.out.println("Implemented in Anonymous Class");
            }
        };

        inner.show(); 
    }

    public static void main(String[] args) {
        Outer4 outer = new Outer4();
        outer.outerMethod();  
    }
}
