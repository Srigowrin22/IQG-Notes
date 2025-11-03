/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.abstractfactorymethod.furniturefactory;

import training.iqgateway.abstractfactorymethod.Chair;
import training.iqgateway.abstractfactorymethod.VictorianChair;
import training.iqgateway.abstractfactorymethod.coffeetable.CoffeeTable;
import training.iqgateway.abstractfactorymethod.coffeetable.VictorianCoffeeTable;

/**
 *
 * @author srigowri.n
 */
public class VictorianFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
    
    public static FurnitureFactory createVictorianFurnitureFactory(){
        return new VictorianFurnitureFactory();
    }
}
