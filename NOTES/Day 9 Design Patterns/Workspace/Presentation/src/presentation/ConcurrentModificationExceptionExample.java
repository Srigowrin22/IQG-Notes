/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author srigowri.n
 */
import java.io.*;
import java.util.*;

class ConcurrentModificationExceptionExample {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("List Value Before Iteration:" + list);

//        for(Integer i : list){
//            if(i==3){
//                list.add(6);
//            }
//        }

        Iterator<Integer> it = list.iterator();

//        while (it.hasNext()) {
//            Integer value = it.next();
//
//            if (value.equals(3))
//                list.remove(value);
//        }

        for (int i = 0; i < list.size(); i++) {
            if (i == 3) {
                list.remove(i); 
            }
        }

        System.out.println("List Value After Iteration:" + list);
    }
}

/*
    This exception rises when an object is tried to be modified concurrently 
    when it is not permissible i.e when one thread is iterating over some collection 
    class object and if some other thread tried to modify or try to make some changes 
    on that collection object then we will get ConcurrentModificationException. 
    This exception usually occurs when we are working with Java collections classes.
 */
