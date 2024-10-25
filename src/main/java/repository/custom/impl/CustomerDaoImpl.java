package repository.custom.impl;

import dto.Customer;
import entity.CustomerEntity;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.custom.CustomerDao;
import util.CrudUtil;

import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(CustomerEntity customer) {
        String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerContact(),
                    customer.getCustomerCity()
            );
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ObservableList<CustomerEntity> getAll() {
        return null;
    }

    @Override
    public boolean update(CustomerEntity customer) {
        return false;
    }

    @Override
    public CustomerEntity search(String id) {
        return null;
    }
}
