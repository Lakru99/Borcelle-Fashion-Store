package controller.order;

import controller.employee.EmployeeController;
import db.DBConnection;
import dto.Order;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            String SQL="INSERT INTO orders VALUE(?,?,?)";
            connection.setAutoCommit(false);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,order.getOrderId());
            psTm.setObject(2,order.getOrderDate());
            psTm.setObject(3,order.getCustomerId());
            boolean isOrderAdd = psTm.executeUpdate()>0;
            if (isOrderAdd){
                boolean isOrderDetailAdd = new OrderDetailsController().addOrderDetail(order.getOrderDetails());
                if (isOrderDetailAdd){
                    boolean isUpdateStock = EmployeeController.getInstance().updateStock(order.getOrderDetails());
                    if (isUpdateStock){
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION,"Order Placed !!").show();
                    }
                }
            }
            //need to clear buffer when all are added!
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
