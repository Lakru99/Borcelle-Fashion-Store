package controller.employee;

import dto.*;
import javafx.collections.ObservableList;

import java.util.List;

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

    boolean addSupplier(Supplier supplier);
    ObservableList<Supplier> getAllSupplier();
    boolean deleteSupplier(String id);
    Supplier searcSupplier(String id);
    boolean updateSupplier(Supplier supplier);
    ObservableList<String> getCustomerIds();
    ObservableList<String> getItemCodes();

    boolean updateStock(List<OrderDetail> orderDetails);

}
