package training.iqgateway.view;

public class LoginBean {
    
    private String username;
    
    private String password;
    
    public LoginBean(){
        
    }

    public LoginBean(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public String toString(){
        return "Loginn Bean [Username: "+ this.username + " \t Password: " + this.password + " \n";
    }

    public String navigate() {
        // Add event code here...
        if(username.equals("IQGateway") && password.equals("1234567")){
            return "success";
        }
        return "failure";
    }
}
