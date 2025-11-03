package training.iqgateway;

public class CustomerDAOFactory {

    private static CustomerDAO customerDAORef;
    
    public static CustomerDAO createCustomerDAO(){
        customerDAORef = new CustomerDAOImpl();
        return customerDAORef;
    }
}
