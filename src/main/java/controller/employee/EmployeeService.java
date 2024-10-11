package controller.employee;

import dto.Customer;
import dto.Employee;
import javafx.collections.ObservableList;

public interface EmployeeService {
    boolean addEmployee(Customer customer);
    ObservableList<Customer> getAllCustomer();
}
