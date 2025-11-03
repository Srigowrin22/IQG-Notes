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
public class CloseFileCommand implements Command {

    private FileSystemReceiver fileSystemRef;
    
    public CloseFileCommand(FileSystemReceiver fs) {
        this.fileSystemRef = fs;
    }
    
    @Override
    public void execute() {
        // Close Command Pattern Forwards Request to CloseFile Method 
        this.fileSystemRef.closeFile();
    }
    
}
