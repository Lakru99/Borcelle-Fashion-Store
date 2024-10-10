package controller.admin;

import db.DBConnection;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController implements AdminService{
    private static AdminController instance;
    private AdminController(){}
    public static AdminController getInstance() {
        return instance == null ? instance = new AdminController() : instance;
    }
    @Override
    public boolean addEmployee(Employee employee) {

        try {
            String SQL = "INSERT INTO employee VALUES(?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, employee.getEmpId());
            pstm.setObject(2,employee.getEmpName());
            pstm.setObject(3,employee.getEmpCompany());
            pstm.setObject(4,employee.getEmpSalary());
            pstm.setObject(5,employee.getEmpEmail());
            pstm.setObject(6,employee.getEmpPassword());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

        return false;
    }

    @Override
    public ObservableList<Employee> getAll() {
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT * FROM employee";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                Employee employee=new Employee(
                        resultSet.getInt("empId"),
                        resultSet.getString("empName"),
                        resultSet.getString("empCompany"),
                        resultSet.getDouble("empSalary"),
                        resultSet.getString("empEmail"),
                        resultSet.getString("empPassword")
                );
                employeeObservableList.add(employee);
                System.out.println(employee);
            }
            return employeeObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        String SQL = "DELETE FROM employee WHERE empId='" + id + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
