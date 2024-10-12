package controller.employee;

import dto.Customer;
import javafx.collections.ObservableList;

public interface EmployeeService {
    boolean addCustomer(Customer customer);
    ObservableList<Customer> getAllCustomer();
    boolean deleteCustomer(String id);

    Customer searchCustomer(String id);
}
