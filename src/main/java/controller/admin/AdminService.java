package controller.admin;

import dto.Employee;
import javafx.collections.ObservableList;

public interface AdminService {
    boolean addEmployee(Employee employee);
    ObservableList<Employee> getAll();
    boolean deleteEmployee(Integer id);


}
