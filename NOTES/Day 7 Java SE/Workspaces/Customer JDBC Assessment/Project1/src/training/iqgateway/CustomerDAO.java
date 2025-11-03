package training.iqgateway;

import java.util.List;

public interface CustomerDAO {
    
    int insertCustomer (CustomerEO custEO);
    
    int updateCustomer (CustomerEO custEO);
        
    int deleteCustomer (Integer custID);

    CustomerEO findCustomerByID(Integer custID);
        
    List<CustomerEO> findAllCustomers();
        
    List<CustomerEO> findCustomerByName (String custName);
}
