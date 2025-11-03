/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
class myThread extends Thread {

    public void run()
    {

        for (int i = 0; i < 5; i++) {
          
            System.out.println("Thread excuting");
        }
    }
}

class IllegalThreadStateExceptionExpm {
  
    public static void main(String[] args)
    {
        myThread t = new myThread();
      
        t.start();
        t.start();
    }
}