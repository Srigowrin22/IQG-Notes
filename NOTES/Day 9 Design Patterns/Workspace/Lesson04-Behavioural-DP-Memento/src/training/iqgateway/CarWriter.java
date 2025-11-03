/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author srigowri.n
 */

public class CarWriter 
{
    public static void main(String[] args) 
		{

		Car obj=new Car();
        
		String str=new String("Hello My Friends In Oracle...");
	
		try 
			{
			FileOutputStream out = new FileOutputStream("ObjectData.txt");
	
			ObjectOutputStream oos = new ObjectOutputStream(out);
	
			oos.writeObject(obj);
		
			oos.writeObject(str);
			
			System.out.println("writing complete");	
		
			oos.flush();
		}
		catch (Exception e)
		{
			 System.out.println("Problem serializing: " + e);
		}
    }
}
