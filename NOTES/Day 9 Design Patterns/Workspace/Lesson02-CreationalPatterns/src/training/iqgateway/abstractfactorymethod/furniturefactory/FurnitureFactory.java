/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package training.iqgateway.abstractfactorymethod.furniturefactory;

import training.iqgateway.abstractfactorymethod.Chair;
import training.iqgateway.abstractfactorymethod.coffeetable.CoffeeTable;

/**
 *
 * @author srigowri.n
 */
public interface FurnitureFactory {

    public abstract Chair createChair();

    public abstract CoffeeTable createCoffeeTable();
}
