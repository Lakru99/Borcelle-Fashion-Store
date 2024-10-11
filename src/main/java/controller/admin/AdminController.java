package controller.admin;

import db.DBConnection;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import util.CrudUtil;

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
                        resultSet.getString("empId"),
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
    public boolean deleteEmployee(String id) {
        String SQL = "DELETE FROM employee WHERE empId='" + id + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<String> getCustomerIds() {
        ObservableList<String> employeeIds = FXCollections.observableArrayList();
        ObservableList<Employee> employeeObservableList = getAll();
        employeeObservableList.forEach(employee -> {
            employeeIds.add(employee.getEmpId());
        });
        return employeeIds;
    }

    @Override
    public Employee searchEmployee(String id) {

        try {
            String SQL = "SELECT * FROM employee WHERE empId=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,id);
            ResultSet resultSet = psTm.executeQuery();
            if(resultSet.next()){
                   return new Employee(
                            resultSet.getString("empId"),
                            resultSet.getString("empName"),
                            resultSet.getString("empCompany"),
                            resultSet.getDouble("empSalary"),
                            resultSet.getString("empEmail"),
                            resultSet.getString("empPassword")
                    );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try {
            String SQL = "UPDATE employee SET empName=?, empCompany=?, empSalary=?, empEmail=?, empPassword=? WHERE empId=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1,employee.getEmpName());
            pstm.setObject(2,employee.getEmpCompany());
            pstm.setObject(3,employee.getEmpSalary());
            pstm.setObject(4,employee.getEmpEmail());
            pstm.setObject(5,employee.getEmpPassword());
            pstm.setObject(6,employee.getEmpId());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return false;
    }
}
