package controller.employee;


import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class employeeLoginFormController {

    @FXML
    private JFXTextField employeeEmailField;

    @FXML
    private JFXTextField employeePasswordlField;

    @FXML
    void btnEmployeeLoginOnAction(ActionEvent event) {
        try {
            String SQL= "SELECT * FROM employee WHERE empEmail=? and empPassword = ?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setString(1, employeeEmailField.getText());
            psTm.setString(2,employeePasswordlField.getText());

            ResultSet resultSet = psTm.executeQuery();
            Alert alert;

            if (employeeEmailField.getText().isEmpty() || employeePasswordlField.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all filed");
                alert.show();
            }else{
                if(resultSet.next()){
                    Stage stage = new Stage();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_employee_form.fxml"))));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Email Or Password");
                    alert.show();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnForgetPasswordOnAction(ActionEvent event) {

    }

}
