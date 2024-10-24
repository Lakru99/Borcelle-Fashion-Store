package controller.employee;

import controller.admin.AdminController;
import db.DBConnection;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController implements EmployeeService{
    private static EmployeeController instance;
    private EmployeeController(){};
    public static EmployeeController getInstance() {
        return instance == null ? instance = new EmployeeController() : instance;
    }

    @Override
    public boolean addCustomer(Customer customer) {

        String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement(SQL);
//            pstm.setObject(1, customer.getCustomerId());
//            pstm.setObject(2,customer.getCustomerName());
//            pstm.setObject(3,customer.getCustomerContact());
//            pstm.setObject(4,customer.getCustomerCity());
//            return pstm.executeUpdate() > 0;
            return CrudUtil.execute(SQL,
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerContact(),
                    customer.getCustomerCity()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<Customer> getAllCustomer() {
        String SQL = "SELECT * FROM customer";
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement psTm = connection.prepareStatement(SQL);
//            ResultSet resultSet = psTm.executeQuery();
//            while (resultSet.next()){
//                Customer customer=new Customer(
//                        resultSet.getString("customerId"),
//                        resultSet.getString("customerName"),
//                        resultSet.getString("customerContactNumber"),
//                        resultSet.getString("customerCity")
//                );
//                customerObservableList.add(customer);
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                customerObservableList.add(
                    new Customer(
                            resultSet.getString("customerId"),
                            resultSet.getString("customerName"),
                            resultSet.getString("customerContactNumber"),
                            resultSet.getString("customerCity")
                    )
                );
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            return connection.createStatement().executeUpdate(SQL) > 0;
            return CrudUtil.execute("DELETE FROM customer WHERE customerId=", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String id) {
        String SQL = "SELECT * FROM customer WHERE customerId=?";
        try {

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement psTm = connection.prepareStatement(SQL);
//            psTm.setObject(1,id);
//            ResultSet resultSet = psTm.executeQuery();
//            if(resultSet.next()){
//                return new Customer(
//                        resultSet.getString("customerId"),
//                        resultSet.getString("customerName"),
//                        resultSet.getString("customerContactNumber"),
//                        resultSet.getString("customerCity")
//                );
//            }
            ResultSet resultSet = CrudUtil.execute(SQL, id);
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
        String SQL = "UPDATE customer SET customerName=?, customerContactNumber=?, customerCity=?  WHERE customerId=?";
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement(SQL);
//
//            pstm.setObject(1,customer.getCustomerName());
//            pstm.setObject(2,customer.getCustomerContact());
//            pstm.setObject(3,customer.getCustomerCity());
//            pstm.setObject(4, customer.getCustomerId());
//            return pstm.executeUpdate() > 0;
            return CrudUtil.execute(SQL,
                    customer.getCustomerName(),
                    customer.getCustomerContact(),
                    customer.getCustomerCity(),
                    customer.getCustomerId()
                    );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean addItem(Item item) {
        String SQL = "INSERT INTO item VALUES(?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    item.getItemCode(),
                    item.getItemDescription(),
                    item.getItemSize(),
                    item.getItemPrice(),
                    item.getItemQty()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<Item> getAllItem() {
        String SQL = "SELECT * FROM item";
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
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
        try {
            return CrudUtil.execute("DELETE FROM item WHERE itemCode=", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item searcItem(String id) {
        String SQL = "SELECT * FROM item WHERE itemCode=?";
        try {

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
        String SQL = "UPDATE item SET itemDescription=?, itemSize=?, itemPrice=?, itemQty=?  WHERE itemCode=?";
        try {
            return CrudUtil.execute(SQL,
                    item.getItemDescription(),
                    item.getItemSize(),
                    item.getItemPrice(),
                    item.getItemQty(),
                    item.getItemCode()
            );
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String SQL = "INSERT INTO supplier VALUES(?,?,?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getSupplierContact(),
                    supplier.getSupplierCompany(),
                    supplier.getSupplierItemCategory(),
                    supplier.getSupplierItemName()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public ObservableList<Supplier> getAllSupplier() {
        String SQL = "SELECT * FROM supplier";
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()){
                supplierObservableList.add(
                        new Supplier(
                                resultSet.getString("supplierId"),
                                resultSet.getString("supplierName"),
                                resultSet.getString("supplierContact"),
                                resultSet.getString("supplierCompany"),
                                resultSet.getString("supplierItemCategory"),
                                resultSet.getString("supplierItemName")
                        )
                );
            }
            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(String id) {
        try {
            return CrudUtil.execute("DELETE FROM supplier WHERE supplierId=", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier searcSupplier(String id) {
        String SQL = "SELECT * FROM supplier WHERE supplierId=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL, id);
            if(resultSet.next()){
                return new Supplier(
                        resultSet.getString("supplierId"),
                        resultSet.getString("supplierName"),
                        resultSet.getString("supplierContact"),
                        resultSet.getString("supplierCompany"),
                        resultSet.getString("supplierItemCategory"),
                        resultSet.getString("supplierItemName")
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return null;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        String SQL = "UPDATE supplier SET supplierName=?, supplierContact=?, supplierCompany=?, supplierItemCategory=? , supplierItemName=?   WHERE supplierId=?";
        try {
            return CrudUtil.execute(SQL,
                    supplier.getSupplierName(),
                    supplier.getSupplierContact(),
                    supplier.getSupplierCompany(),
                    supplier.getSupplierItemCategory(),
                    supplier.getSupplierItemName(),
                    supplier.getSupplierId()
            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Error : " + e.getMessage()).show();
        }
        return false;
    }
    public ObservableList<String> getCustomerIds() {
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        ObservableList<Customer> customerObservableList = getAllCustomer();
        customerObservableList.forEach(customer -> {
            customerIds.add(customer.getCustomerId());
        });

        return customerIds;
    }
    @Override
    public ObservableList<String> getItemCodes() {
        ObservableList<String> itemCodes = FXCollections.observableArrayList();
        ObservableList<Item> itemObservableList = getAllItem();
        itemObservableList.forEach(item -> {
            itemCodes.add(item.getItemCode());
        });

        return itemCodes;
    }
    @Override
    public boolean updateStock(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail: orderDetails){
            boolean updateStock = updateStock(orderDetail);
            if (!updateStock){
                return false;
            }
        }
        return true;
    }
    public boolean updateStock(OrderDetail orderDetails){
        String SQL = "UPDATE Item SET itemQty=itemQty-? WHERE ItemCode=?";
        try {
            return CrudUtil.execute(SQL,
                    orderDetails.getQty(),
                    orderDetails.getItemCode()
            );
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
