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
public class OpenFileCommand implements Command {

    private FileSystemReceiver fileSystemRef;
    
    public OpenFileCommand(FileSystemReceiver fs) {
        this.fileSystemRef = fs;
    }
    
    @Override
    public void execute() {
        // Open Command Pattern Forwards Request to OpenFile Method 
        this.fileSystemRef.openFile();
    }
    
}
