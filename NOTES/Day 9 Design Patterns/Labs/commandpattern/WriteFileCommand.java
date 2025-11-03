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
public class WriteFileCommand implements Command {

    private FileSystemReceiver fileSystemRef;
    
    public WriteFileCommand(FileSystemReceiver fs) {
        this.fileSystemRef = fs;
    }
    
    @Override
    public void execute() {
        // Write Command Pattern Forwards Request to WriteFile Method 
        this.fileSystemRef.writeFile();
    }
    
}
