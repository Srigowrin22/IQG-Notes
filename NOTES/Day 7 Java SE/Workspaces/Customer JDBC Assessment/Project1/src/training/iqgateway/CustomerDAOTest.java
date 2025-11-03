package training.iqgateway;

public class CustomerDAOTest {
    private CustomerDAO custDAORef = CustomerDAOFactory.createCustomerDAO();   
    
    public void testInsertCustomer(){
        CustomerEO custEO = new CustomerEO(1, "Srigowri");
        int result = custDAORef.insertCustomer(custEO);
        if(result > 0){
            System.out.println("Record Inseted");
        }else{
            System.out.print("Record insertion failed");
        }
    }
}
