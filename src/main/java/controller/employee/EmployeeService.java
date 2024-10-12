package controller.employee;

import dto.Customer;
import dto.Employee;
import javafx.collections.ObservableList;

public interface EmployeeService {
    boolean addCustomer(Customer customer);
    ObservableList<Customer> getAllCustomer();
    boolean deleteCustomer(String id);
}
