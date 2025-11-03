package training.iqgateway.service;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerEO createCustomer(CustomerEO customer);

    Optional<CustomerEO> getCustomerById(Integer id);

    List<CustomerEO> getAllCustomers();

    CustomerEO updateCustomer(Integer id, CustomerEO customer);

    void deleteCustomer(Integer id);
}