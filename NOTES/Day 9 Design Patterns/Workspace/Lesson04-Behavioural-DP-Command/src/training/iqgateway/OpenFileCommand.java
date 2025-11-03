/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
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
