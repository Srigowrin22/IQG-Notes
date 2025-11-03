/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

import java.io.Serializable;

/**
 *
 * @author srigowri.n
 */
class Car implements Serializable
{
	public String Model;
	public transient float price;
	public int speed;
	
	public static int nextID = 1000;
	
	public Car()
	{
		speed=240;
		Model="Tata Safari";
		price=1800000;
	}
}
