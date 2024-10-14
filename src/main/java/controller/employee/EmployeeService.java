package controller.employee;

import dto.Customer;
import dto.Employee;
import dto.Item;
import javafx.collections.ObservableList;

public interface EmployeeService {
    boolean addCustomer(Customer customer);
    ObservableList<Customer> getAllCustomer();
    boolean deleteCustomer(String id);
    Customer searchCustomer(String id);
    boolean updateCustomer(Customer customer);

    boolean addItem(Item item);
    ObservableList<Item> getAllItem();
    boolean deleteItem(String id);
    Item searcItem(String id);
    boolean updateItem(Item item);

}
