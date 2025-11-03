/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class ProxyPatternTester {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutorProxy("Rahul", "rahul123");
        
        try{
            executor.runCommand("cmd.exe dir");
            executor.runCommand("cmd.exe mkdir");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
