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
public class FileInvoker {
    
    public Command commandRef;

    public FileInvoker(Command commandRef) {
        this.commandRef = commandRef;
    }
    
    public void execute() {
        this.commandRef.execute();
    }
    
}
