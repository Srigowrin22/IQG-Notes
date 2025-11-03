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
public class CommandPatternTester {
    
    public static void main(String[] args) {
        
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();
        OpenFileCommand openFileCommandRef = new OpenFileCommand(fs);
        FileInvoker fileInvokerRef1 = new FileInvoker(openFileCommandRef);
        // Invocation
        fileInvokerRef1.execute();
        
        WriteFileCommand writeFileCommandRef = new WriteFileCommand(fs);
        FileInvoker fileInvokerRef2 = new FileInvoker(writeFileCommandRef);
        // Invocation
        fileInvokerRef2.execute();
        
        
        
    }
    
}
