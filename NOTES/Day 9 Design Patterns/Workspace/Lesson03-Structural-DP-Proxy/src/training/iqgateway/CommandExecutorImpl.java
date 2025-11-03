/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class CommandExecutorImpl implements CommandExecutor {
    public void runCommand(String cmd) throws Exception{
        System.out.println("'" + cmd + "' Command Executed..." );
    }
}
