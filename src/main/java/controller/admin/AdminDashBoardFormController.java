package controller.admin;

import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashBoardFormController implements Initializable {

    @FXML
    private JFXTextField empIdFiled;

    @FXML
    private JFXTextField empNameField;

    @FXML
    private JFXTextField empCompanyField;

    @FXML
    private JFXTextField empSalaryField;

    @FXML
    private TableView<Employee> empTbl;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private TableColumn<?, ?> colEmpCompany;

    @FXML
    private TableColumn<?, ?> colEmpSalary;

    @FXML
    private TableColumn<?, ?> colEmpEmail;

    @FXML
    private TableColumn<?, ?> colEmpPassword;

    @FXML
    private JFXTextField empEmailField;

    @FXML
    private JFXTextField empPasswordField;

    AdminService service = AdminController.getInstance();

    @FXML
    void btnEmpAddOnAction(ActionEvent event) {
        Employee employee=new Employee(
                Integer.parseInt(empIdFiled.getText()),
                empNameField.getText(),
                empCompanyField.getText(),
                Double.parseDouble(empSalaryField.getText()),
                empEmailField.getText(),
                empPasswordField.getText()
        );
        if (service.addEmployee(employee)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Added !!").show();
            loadTable();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Added :(").show();
        }
    }

    @FXML
    void btnEmpDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmpUpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colEmpCompany.setCellValueFactory(new PropertyValueFactory<>("empCompany"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("empEmail"));
        colEmpPassword.setCellValueFactory(new PropertyValueFactory<>("empPassword"));
        empTbl.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    private void setTextToValues(Employee newValue) {
        empIdFiled.setText(String.valueOf(newValue.getEmpId()));
        empNameField.setText(newValue.getEmpName());
        empCompanyField.setText(newValue.getEmpCompany());
        empSalaryField.setText(String.valueOf(newValue.getEmpSalary()));
        empEmailField.setText(newValue.getEmpEmail());
        empPasswordField.setText(newValue.getEmpPassword());
    }

    private void loadTable() {
        ObservableList<Employee> employeeObservableList =service.getAll();
        empTbl.setItems(employeeObservableList);
    }
}
