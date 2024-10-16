package controller.employee;

import controller.admin.AdminController;
import db.DBConnection;
import dto.Customer;
import dto.Employee;
import dto.Item;
import dto.Supplier;
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

    @Override
    public Customer searchCustomer(String id) {
        try {
            String SQL = "SELECT * FROM customer WHERE customerId=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,id);
            ResultSet resultSet = psTm.executeQuery();
            if(resultSet.next()){
                return new Customer(
                        resultSet.getString("customerId"),
                        resultSet.getString("customerName"),
                        resultSet.getString("customerContactNumber"),
                        resultSet.getString("customerCity")
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            String SQL = "UPDATE customer SET customerName=?, customerContactNumber=?, customerCity=?  WHERE customerId=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1,customer.getCustomerName());
            pstm.setObject(2,customer.getCustomerContact());
            pstm.setObject(3,customer.getCustomerCity());
            pstm.setObject(4, customer.getCustomerId());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean addItem(Item item) {
        try {
            String SQL = "INSERT INTO item VALUES(?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, item.getItemCode());
            pstm.setObject(2, item.getItemDescription());
            pstm.setObject(3,item.getItemSize());
            pstm.setObject(4,item.getItemPrice());
            pstm.setObject(5,item.getItemQty());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<Item> getAllItem() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM item";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                Item item=new Item(
                        resultSet.getString("itemCode"),
                        resultSet.getString("itemDescription"),
                        resultSet.getString("itemSize"),
                        resultSet.getDouble("itemPrice"),
                        resultSet.getInt("itemQty")

                );
                itemObservableList.add(item);
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String id) {
        String SQL = "DELETE FROM item WHERE itemCode='" + id + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searcItem(String id) {
        try {
            String SQL = "SELECT * FROM item WHERE itemCode=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,id);
            ResultSet resultSet = psTm.executeQuery();
            if(resultSet.next()){
                return new Item(
                        resultSet.getString("itemCode"),
                        resultSet.getString("itemDescription"),
                        resultSet.getString("itemSize"),
                        resultSet.getDouble("itemPrice"),
                        resultSet.getInt("itemQty")
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return null;
    }

    @Override
    public boolean updateItem(Item item) {
        try {
            String SQL = "UPDATE item SET itemDescription=?, itemSize=?, itemPrice=?, itemQty=?  WHERE itemCode=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1,item.getItemDescription());
            pstm.setObject(2,item.getItemSize());
            pstm.setObject(3,item.getItemPrice());
            pstm.setObject(4, item.getItemQty());
            pstm.setObject(5, item.getItemCode());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        try {
            String SQL = "INSERT INTO supplier VALUES(?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1, supplier.getSupplierId());
            pstm.setObject(2, supplier.getSupplierName());
            pstm.setObject(3,supplier.getSupplierContact());
            pstm.setObject(4,supplier.getSupplierCompany());
            pstm.setObject(5,supplier.getSupplierItemCategory());
            pstm.setObject(6,supplier.getSupplierItemName());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<Supplier> getAllSupplier() {
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM supplier";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                Supplier supplier=new Supplier(
                        resultSet.getString("supplierId"),
                        resultSet.getString("supplierName"),
                        resultSet.getString("supplierContact"),
                        resultSet.getString("supplierCompany"),
                        resultSet.getString("supplierItemCategory"),
                        resultSet.getString("supplierItemName")

                );
                supplierObservableList.add(supplier);
            }
            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(String id) {
        return false;
    }

    @Override
    public Supplier searcSupplier(String id) {
        return null;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return false;
    }
}
