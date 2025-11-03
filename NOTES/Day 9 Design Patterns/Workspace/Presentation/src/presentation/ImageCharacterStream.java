/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author srigowri.n
 */
public class ImageCharacterStream {
    public static void main(String[] args) {
        String imgRead = "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Presentation\\beach.jpg";
        String imgWrite = "C:\\Users\\srigowri.n\\OneDrive - iqgateway pvt ltd\\Desktop\\Presentation\\beachCopy.jpg";

        // Reading characters from a file using character stream
//        try {
//            FileReader fr = new FileReader(imgRead);
//            FileWriter fw = new FileWriter(imgWrite);
//            int charData;
//            while ((charData = fr.read()) != -1) {
//                fw.write(charData);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        // Reading characters from a file using ByteStream

        try {
            FileInputStream fr = new FileInputStream(imgRead);
            FileOutputStream fw = new FileOutputStream(imgWrite);
            int charData;
            while ((charData = fr.read()) != -1) {
                fw.write(charData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
