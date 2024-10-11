package controller.admin;

import dto.Employee;
import javafx.collections.ObservableList;

public interface AdminService {
    boolean addEmployee(Employee employee);
    ObservableList<Employee> getAll();
    boolean deleteEmployee(String id);

    ObservableList<String> getCustomerIds();

    Employee searchEmployee(String id);

    boolean updateEmployee(Employee employee);
}
