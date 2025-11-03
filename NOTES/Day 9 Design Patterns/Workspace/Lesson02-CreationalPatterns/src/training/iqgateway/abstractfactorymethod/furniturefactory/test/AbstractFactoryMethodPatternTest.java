/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.abstractfactorymethod.furniturefactory.test;

import training.iqgateway.abstractfactorymethod.Chair;
import training.iqgateway.abstractfactorymethod.coffeetable.CoffeeTable;
import training.iqgateway.abstractfactorymethod.furniturefactory.FurnitureFactory;
import training.iqgateway.abstractfactorymethod.furniturefactory.VictorianFurnitureFactory;

/**
 *
 * @author srigowri.n
 */
public class AbstractFactoryMethodPatternTest {
    public static void main(String[] args) {
        
//        FurnitureFactory victorianFurnitureFactoryRef = new VictorianFurnitureFactory();
        
    FurnitureFactory victorianFurnitureFactoryRef = VictorianFurnitureFactory.createVictorianFurnitureFactory();
        
        Chair vicChair = victorianFurnitureFactoryRef.createChair();
        System.out.println("Has Legs: " + vicChair.hasLeg());
        vicChair.sitON();
        
        CoffeeTable vicCoffeeTable = victorianFurnitureFactoryRef.createCoffeeTable();
        System.out.println("Shape: " + vicCoffeeTable.shape());
        System.err.println("Top: " + vicCoffeeTable.madeUpOF());
    }
}
