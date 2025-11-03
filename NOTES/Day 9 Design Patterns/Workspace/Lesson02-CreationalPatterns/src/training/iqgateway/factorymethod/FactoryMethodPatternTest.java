/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.factorymethod;

/**
 *
 * @author srigowri.n
 */
public class FactoryMethodPatternTest {

    public static void main(String[] args) {

        // Laptop lap = new Laptop();
        WorkStation laptopRef = WorkStationFactory.getWorkStation("Laptop", "8GB", "480GB", "2.4GHz");

        //Server server = new Server();
        WorkStation serverRef = WorkStationFactory.getWorkStation("Server", "64GB", "100TB", "3.0GHz");

         //Desktop desktop = new Desktop();
        WorkStation desktopRef = WorkStationFactory.getWorkStation("Desktop", "12GB", "10GB", "4.0GHz");

        System.out.println("Factory Laptop Config Details: " + laptopRef);
        System.out.println("Factory Server Config Details: " + serverRef);
        System.err.println("Factory Desktop Config Details: " +desktopRef);


    }

}
