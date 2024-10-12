package controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Customer;
import dto.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;



public class EmployeeDashboardFormController implements Initializable {

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnOrderDashboard;

    @FXML
    private JFXButton btnProductDashboard;

    @FXML
    private JFXButton btnCustomerDashboard;

    @FXML
    private JFXButton btnSuplierDashboard;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private AnchorPane orderForm;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TextField txtOrderId;

    @FXML
    private ComboBox<?> cmbCustomerID;

    @FXML
    private ComboBox<?> cmbItemCode;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtItemDescription;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPriceOnOrder;

    @FXML
    private TextField txtQtyOnOrder;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TableColumn<?, ?> colOrderItemCode;

    @FXML
    private TableColumn<?, ?> colOrderDescription;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableColumn<?, ?> colOrderUnitPrice;

    @FXML
    private TableColumn<?, ?> colOrderTotal;

    @FXML
    private Label lblNetTotal;

    @FXML
    private AnchorPane productForm;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtUnitPriceProduct;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TableView<?> tblProducts;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemDescription;

    @FXML
    private TableColumn<?, ?> colItemSize;

    @FXML
    private TableColumn<?, ?> colItemUnitPrice;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private AnchorPane customerForm;

    @FXML
    private JFXTextField customerIdField;

    @FXML
    private JFXTextField customerNameField;

    @FXML
    private JFXTextField customerContactField;

    @FXML
    private JFXTextField customerCityField;

    @FXML
    private TableView<?> tblProducts1;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerContactNumber;

    @FXML
    private TableColumn<?, ?> colCustomerCity;

    @FXML
    private AnchorPane suplierForm;

    @FXML
    private JFXTextField suplierIdFiled;

    @FXML
    private JFXTextField suplierNameFiled;

    @FXML
    private JFXTextField suplierContactField;

    @FXML
    private JFXTextField suplierCompanyField;

    @FXML
    private TableView<?> tblProducts11;

    @FXML
    private TableColumn<?, ?> colSuplierID;

    @FXML
    private TableColumn<?, ?> colSuplierName;

    @FXML
    private TableColumn<?, ?> colSuplierContact;

    @FXML
    private TableColumn<?, ?> colSuplierCompany;

    @FXML
    private TableColumn<?, ?> colSuplierItem;

    @FXML
    private JFXTextField suplierItemField;

    EmployeeService employeeService = EmployeeController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeForm.setVisible(true);
        orderForm.setVisible(false);
        productForm.setVisible(false);
        customerForm.setVisible(false);
        suplierForm.setVisible(false);

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerContactNumber.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        colCustomerCity.setCellValueFactory(new PropertyValueFactory<>("customerCity"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setCustomerTextToValues(newValue);
        }));
        loadTableCustomer();
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        Customer customer=new Customer(
                customerIdField.getText(),
                customerNameField.getText(),
                customerContactField.getText(),
                customerCityField.getText()
        );
        if (employeeService.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added !!").show();
            loadTableCustomer();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Added :(").show();
        }
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        if (employeeService.deleteCustomer((customerIdField.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted !!").show();
            loadTableCustomer();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
    }
    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) {
            Customer customer = employeeService.searchCustomer(customerIdField.getText());
            setCustomerTextToValues(customer);
    }
    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
            Customer customer=new Customer(
                    customerIdField.getText(),
                    customerNameField.getText(),
                    customerContactField.getText(),
                    customerCityField.getText()
            );
            employeeService.updateCustomer((customer));
            loadTableCustomer();
    }
    private void setCustomerTextToValues(Customer newValue) {
        customerIdField.setText(newValue.getCustomerId());
        customerNameField.setText(newValue.getCustomerName());
        customerContactField.setText(newValue.getCustomerContact());
        customerCityField.setText(newValue.getCustomerCity());
    }

    private void loadTableCustomer() {
        ObservableList<Customer> customerObservableList = employeeService.getAllCustomer();
        tblCustomers.setItems(customerObservableList);
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddSuplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }



    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSuplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }



    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSuplierOnAction(ActionEvent event) {

    }



    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSuplierOnAction(ActionEvent event) {

    }

    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource()==btnHome){
            homeForm.setVisible(true);
            orderForm.setVisible(false);
            productForm.setVisible(false);
            customerForm.setVisible(false);
            suplierForm.setVisible(false);
        } else if (event.getSource()==btnOrderDashboard) {
            homeForm.setVisible(false);
            orderForm.setVisible(true);
            productForm.setVisible(false);
            customerForm.setVisible(false);
            suplierForm.setVisible(false);
        }else if (event.getSource()==btnProductDashboard) {
            homeForm.setVisible(false);
            orderForm.setVisible(false);
            productForm.setVisible(true);
            customerForm.setVisible(false);
            suplierForm.setVisible(false);
        }else if (event.getSource()==btnCustomerDashboard) {
            homeForm.setVisible(false);
            orderForm.setVisible(false);
            productForm.setVisible(false);
            customerForm.setVisible(true);
            suplierForm.setVisible(false);
        }else {
            homeForm.setVisible(false);
            orderForm.setVisible(false);
            productForm.setVisible(false);
            customerForm.setVisible(false);
            suplierForm.setVisible(true);
        }
    }




}
