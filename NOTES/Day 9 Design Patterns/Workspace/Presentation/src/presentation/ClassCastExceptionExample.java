/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
class ClassCastExecptionExample {
  
    public static void main(String[] args)
    {
        try {
            
            Object o = new Object();
            String s = (String)o;
          
            System.out.println(s);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("ClassCastExceptionExample Demo!");
    }
}

/*
    In the below program we create an object o of type Object and typecasting that object o 
    to a String object s. As we know that Object class is the parent class of all classes 
    in java and as we're trying to typecast a parent object to its child type then ultimately 
    we get java.lang.ClassCastException
*/