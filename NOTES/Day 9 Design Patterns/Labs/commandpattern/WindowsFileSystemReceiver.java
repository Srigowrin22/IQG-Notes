/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.iqgateway.commandpattern;

/**
 *
 * @author Sai Baba
 */
public class WindowsFileSystemReceiver implements FileSystemReceiver {

    @Override
    public void openFile() {
           System.out.println("Opening File in Windows OS ... ");
    }

    @Override
    public void writeFile() {
        System.out.println("Writing File in Windows OS ... ");
    }

    @Override
    public void closeFile() {
        System.out.println("Closing File in Windows OS ... ");
    }
    
}
