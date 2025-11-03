package training.iqgateway;

public class AppEntry {
    public static void main(String[] args) {
        System.out.println("Order Entry Application");
        
        Customer cust1 = new Customer(1001, "Rahul", "Bengaluru", "987654321");
        cust1.setName("Rahul Dravid");
        
        String Customer_name = cust1.getName();
    }
}
