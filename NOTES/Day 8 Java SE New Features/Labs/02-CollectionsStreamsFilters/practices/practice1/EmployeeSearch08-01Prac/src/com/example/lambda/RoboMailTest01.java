package com.example.lambda;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Oracle
 * Reuse lambda expressions
 */
public class RoboMailTest01 {
  
  public static void main(String[] args) {
    
    List<Employee> pl = Employee.createShortList();
    RoboMail01 robo = new RoboMail01();

    Predicate<Employee> salesExecutives = 
      p -> p.getRole().equals(Role.EXECUTIVE) 
        && p.getDept().equals("Sales");
    
    Predicate<Employee> salesEmployeesOver50 = 
      p -> p.getAge() >= 50 && p.getDept().equals("Sales");
    
//    System.out.println("\n==== RoboMail 01");
//    System.out.println("\n=== Sales Execs ===");
//    robo.mail(pl, salesExecutives); // Convert to stream and forEach
//    robo.text(pl, salesExecutives); // Convert to stream and forEach
//    
//    
//    System.out.println("\n=== All Sales ===");
//    robo.mail(pl, salesEmployeesOver50); // Convert to stream and forEach
//    robo.text(pl, salesEmployeesOver50); // Convert to stream and forEach  

    // Excersice : 2-1
//      System.out.println("\n=== Email all sales executives ===");
//        pl.stream()
//            .filter(salesExecutives)
//            .forEach(p -> robo.roboMail(p));
//
//      System.out.println("\n=== Text all sales executives ===");
//        pl.stream()
//            .filter(salesExecutives)
//            .forEach(p -> robo.roboText(p));
//

    // Excersice : 2-2
//      System.out.println("\n=== Email all sales employees over 50 ===");
//        pl.stream()
//            .filter(salesEmployeesOver50)
//            .forEach(p -> robo.roboMail(p));
//
//      System.out.println("\n=== Text all sales employees over 50 ===");
//        pl.stream()
//            .filter(salesEmployeesOver50)
//            .forEach(p -> robo.roboText(p));


    // Excercise : 2-3
        List<Employee> employees = Employee.createShortList();
        System.out.println("RoboMail 01");
        System.out.println("==== All Sales 50+");

        employees.stream()
            .filter(e -> e.getDept().equals("Sales"))   // first filter: department is Sales
            .filter(e -> e.getAge() > 50)                // second filter: age greater than 50
            .forEach(e -> robo.roboMail(e));             // mail each filtered employee
  }
}
