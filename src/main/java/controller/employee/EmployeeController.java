package controller.employee;

import controller.admin.AdminController;
import db.DBConnection;
import dto.Customer;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController implements EmployeeService{
    private static EmployeeController instance;
    private EmployeeController(){};
    public static EmployeeController getInstance() {
        return instance == null ? instance = new EmployeeController() : instance;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, customer.getCustomerId());
            pstm.setObject(2,customer.getCustomerName());
            pstm.setObject(3,customer.getCustomerContact());
            pstm.setObject(4,customer.getCustomerCity());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<Customer> getAllCustomer() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM customer";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                Customer customer=new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("customerName"),
                        resultSet.getString("customerContactNumber"),
                        resultSet.getString("customerCity")
                );
                customerObservableList.add(customer);
                //System.out.println(customer);
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        String SQL = "DELETE FROM customer WHERE customerId='" + id + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
