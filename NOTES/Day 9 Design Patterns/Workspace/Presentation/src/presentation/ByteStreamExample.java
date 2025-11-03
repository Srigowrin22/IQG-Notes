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

public class ByteStreamExample {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Presentation\\ByteStreamExample.txt";
        
        // Writing bytes to a file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            
            byte[] data = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74}; // 'A', 'B', 'C', 'D'... 'J'
            String str = "\nWhy iQGateway?\n" + "       At iQGateway, we consider the pursuit of excellence as an ongoing journey that encompasses three foundational aspects \n       we nurture constantly: Knowledge, Skills and Attitude. It is in equal measure that each is needed to make the journey fruitful.\n";
            
            fos.write(data);
            fos.write(str.getBytes()); 
            System.out.println("Data written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading bytes from a file
        try (FileInputStream fis = new FileInputStream(filePath)) {
            int byteData;
            
            System.out.println("\nAvailable bytes: " + fis.available()); 
            
            long skipped = fis.skip(2);
            System.out.println("\nSkipped " + skipped + " bytes");
            
            System.out.print("\nAfter skip: " + (char)fis.read() + " "); // C
            
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData + " "); 
            }
            
            System.out.println("\nAvailable bytes: " + fis.available()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
