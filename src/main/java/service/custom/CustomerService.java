package service.custom;

import dto.Customer;
import service.SuperService;

public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);
}
