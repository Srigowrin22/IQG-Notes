/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway.abstractfactorymethod.furniturefactory;

import training.iqgateway.abstractfactorymethod.Chair;
import training.iqgateway.abstractfactorymethod.ModernChair;
import training.iqgateway.abstractfactorymethod.coffeetable.CoffeeTable;
import training.iqgateway.abstractfactorymethod.coffeetable.ModernCoffeeTable;

/**
 *
 * @author srigowri.n
 */
public class MordernFurnitureFactory implements FurnitureFactory {

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
    
}
