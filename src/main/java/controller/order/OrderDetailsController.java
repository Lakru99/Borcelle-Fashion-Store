package controller.order;

import db.DBConnection;
import dto.OrderDetail;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsController {
    public boolean addOrderDetail(List<OrderDetail> orderDetails){
        for (OrderDetail orderDetail:orderDetails){
            boolean isAdd=addOrderDetail(orderDetail);
            if (!isAdd){
                return false;
            }
        }
        return true;
    }
    public boolean addOrderDetail(OrderDetail orderDetails){
        String SQL = "INSERT INTO orderdetail VALUES(?,?,?,?)";
        try {

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement(SQL);
//            pstm.setObject(1, orderDetails.getOrderId());
//            pstm.setObject(2,orderDetails.getItemCode());
//            pstm.setObject(3,orderDetails.getQty());
//            pstm.setObject(4,orderDetails.getDiscount());
//            return pstm.executeUpdate() > 0;
            return CrudUtil.execute(SQL,
                    orderDetails.getOrderId(),
                    orderDetails.getItemCode(),
                    orderDetails.getQty(),
                    orderDetails.getDiscount()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
