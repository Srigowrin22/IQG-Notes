/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package training.iqgateway;

/**
 *
 * @author srigowri.n
 */
public class CommandExecutorProxy implements CommandExecutor{
   private boolean isAdmin;
   private CommandExecutor executor;
    
    public CommandExecutorProxy(String username, String pwd) {
        if(username.equals("Rahul") && pwd.equals("rahul123")) {
            isAdmin=true;
            executor = new CommandExecutorImpl();
        }
    }
    
    @Override
    public void runCommand(String cmd) throws Exception {
        if(isAdmin) {
            if(cmd.contains("mk")) {
                throw new Exception("mk Command is Not Allowed ...");
            }
            else
            {
                executor.runCommand(cmd);
            }
            
        }
        else {
            if(cmd.contains("mk")) {
                throw new Exception("mk Command is Not Allowed ...");
            }
            else
            {
                executor.runCommand(cmd);
            }
        }
    } 
}
