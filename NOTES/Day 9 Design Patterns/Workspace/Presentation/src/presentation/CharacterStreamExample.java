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

public class CharacterStreamExample {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Presentation\\CharacterStreamExample.txt";
        
        // Writing characters to a file
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("Hello, Java IO!");
            System.out.println("Text written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading characters from a file
        try (FileReader fr = new FileReader(filePath)) {
            int charData;
            while ((charData = fr.read()) != -1) {
                System.out.print((char) charData); // Output: Hello, Java IO!
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
