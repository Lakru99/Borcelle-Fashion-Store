package controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
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
    private ComboBox<String> cmbCustomerID;

    @FXML
    private ComboBox<String> cmbItemCode;

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
    private TableView<CartTM> tblCart;

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
    private TableView<Item> tblProducts;

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
    private TableView<Supplier> tblSupplier;

    @FXML
    private TableColumn<?, ?> colSuplierID;

    @FXML
    private TableColumn<?, ?> colSuplierName;

    @FXML
    private TableColumn<?, ?> colSuplierContact;

    @FXML
    private TableColumn<?, ?> colSuplierCompany;
    @FXML
    private TableColumn<?, ?> colItemCategory;

    @FXML
    private TableColumn<?, ?> colSuplierItem;

    @FXML
    private JFXTextField suplierItemField;
    @FXML
    private JFXComboBox<String> categoryField;

    EmployeeService employeeService = EmployeeController.getInstance();
    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIds();
        loadItemCodes();
        homeForm.setVisible(true);
        orderForm.setVisible(false);
        productForm.setVisible(false);
        customerForm.setVisible(false);
        suplierForm.setVisible(false);

        ObservableList<String> categoryTitles = FXCollections.observableArrayList();
        categoryTitles.add("Gents");
        categoryTitles.add("Ladies");
        categoryTitles.add("Kids");
        categoryField.setItems(categoryTitles);


        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerContactNumber.setCellValueFactory(new PropertyValueFactory<>("customerContact"));
        colCustomerCity.setCellValueFactory(new PropertyValueFactory<>("customerCity"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setCustomerTextToValues(newValue);
        }));
        loadTableCustomer();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemSize.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));

        tblProducts.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setItemTextToValues(newValue);
        }));
        loadTableItem();


        colSuplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSuplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSuplierContact.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        colSuplierCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("supplierItemCategory"));
        colSuplierItem.setCellValueFactory(new PropertyValueFactory<>("supplierItemName"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setSupplierTextToValues(newValue);
        }));
        loadTableSupplier();

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                searchCustomer(newVal);
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                searchItem(newVal);
            }
        });
    }



    private void clearCustomerFormFields(){
        customerIdField.setPromptText("ID");
        customerNameField.setPromptText("Name");
        customerContactField.setPromptText("Contact Number");
        customerCityField.setText("City");
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
            clearCustomerFormFields();
        }else {
            new Alert(Alert.AlertType.ERROR,"Customer Not Added :(").show();
        }
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        if (employeeService.deleteCustomer((customerIdField.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted !!").show();
            clearCustomerFormFields();
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
            employeeService.updateCustomer(customer);
           loadTableCustomer();
        setCustomerTextToValues(customer);
    }
    private void setCustomerTextToValues(Customer newValue) {
        if (newValue != null) {
            customerIdField.setText(newValue.getCustomerId());
            customerNameField.setText(newValue.getCustomerName());
            customerContactField.setText(newValue.getCustomerContact());
            customerCityField.setText(newValue.getCustomerCity());
            System.out.println(newValue.getCustomerId());
        }else{
            System.out.println("Customer is null");
        }
    }

    private void loadTableCustomer() {
        ObservableList<Customer> customerObservableList = employeeService.getAllCustomer();
        tblCustomers.setItems(customerObservableList);
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Item item=new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtSize.getText(),
                Double.parseDouble(txtUnitPriceProduct.getText()),
                Integer.parseInt(txtQty.getText())
        );
        if (employeeService.addItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Added !!").show();
            loadTableItem();
            clearCustomerFormFields();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Not Added :(").show();
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        if (employeeService.deleteItem((txtItemCode.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted !!").show();
            clearCustomerFormFields();
            loadTableItem();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
    }
    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        Item item = employeeService.searcItem(txtItemCode.getText());
        setItemTextToValues(item);
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        Item item=new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtSize.getText(),
                Double.parseDouble(txtUnitPriceProduct.getText()),
                Integer.parseInt(txtQty.getText())
        );
        employeeService.updateItem(item);
        loadTableItem();
        setItemTextToValues(item);
    }



    private void loadTableItem() {
        ObservableList<Item> itemObservableList = employeeService.getAllItem();
        tblProducts.setItems(itemObservableList);
    }
    private void setItemTextToValues(Item newValue) {
        if (newValue != null) {
            txtItemCode.setText(newValue.getItemCode());
            txtDescription.setText(newValue.getItemDescription());
            txtSize.setText(newValue.getItemSize());
            txtUnitPriceProduct.setText(String.valueOf(newValue.getItemPrice()));
            txtQty.setText(String.valueOf(newValue.getItemQty()));

        }else{
            System.out.println("Item is null");
        }
    }
    @FXML
    void btnAddSuplierOnAction(ActionEvent event) {
        Supplier supplier=new Supplier(
                suplierIdFiled.getText(),
                suplierNameFiled.getText(),
                suplierContactField.getText(),
                suplierCompanyField.getText(),
                categoryField.getValue(),
                suplierItemField.getText()
        );
        if (employeeService.addSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Suplier Added !!").show();
            loadTableSupplier();
        }else {
            new Alert(Alert.AlertType.ERROR,"Suplier Not Added :(").show();
        }
    }

    @FXML
    void btnDeleteSuplierOnAction(ActionEvent event) {
        if (employeeService.deleteSupplier((suplierIdFiled.getText()))){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Deleted !!").show();
//            clearCustomerFormFields();
            loadTableSupplier();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @FXML
    void btnSearchSuplierOnAction(ActionEvent event) {
        Supplier supplier = employeeService.searcSupplier(suplierIdFiled.getText());
        setSupplierTextToValues(supplier);
    }

    @FXML
    void btnUpdateSuplierOnAction(ActionEvent event) {
        Supplier supplier=new Supplier(
                suplierIdFiled.getText(),
                suplierNameFiled.getText(),
                suplierContactField.getText(),
                suplierCompanyField.getText(),
                categoryField.getValue(),
                suplierItemField.getText()
        );
        employeeService.updateSupplier(supplier);
        loadTableSupplier();
        setSupplierTextToValues(supplier);
    }

    private void loadTableSupplier() {
        ObservableList<Supplier> supplierObservableList = employeeService.getAllSupplier();
        tblSupplier.setItems(supplierObservableList);
    }

    private void setSupplierTextToValues(Supplier newValue) {
        if (newValue != null) {
            suplierIdFiled.setText(newValue.getSupplierId());
            suplierNameFiled.setText(newValue.getSupplierName());
            suplierContactField.setText(newValue.getSupplierContact());
            suplierCompanyField.setText(newValue.getSupplierCompany());
            categoryField.setValue(newValue.getSupplierItemCategory());
            suplierItemField.setText(newValue.getSupplierItemName());

        }else{
            System.out.println("Supplier is null");
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }



    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

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


    private void calcNetTotal() {
        Double total=0.0;

        for (CartTM cartTM: cartTMS){
            total+=cartTM.getTotal();
        }
        lblNetTotal.setText("LKR. " + total.toString());
    }

    private void loadDateAndTime(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow =f.format(date);

        lblDate.setText(dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private void loadCustomerIds(){
        cmbCustomerID.setItems(EmployeeController.getInstance().getCustomerIds());
    }
    private void searchCustomer(String customerID){
        Customer customer = employeeService.searchCustomer(customerID);
        txtCustomerName.setText(customer.getCustomerName());
        txtCustomerAddress.setText(customer.getCustomerCity());
        System.out.println(customerID);
    }
//
    private void loadItemCodes(){
        cmbItemCode.setItems(EmployeeController.getInstance().getItemCodes());
    }
    private void searchItem(String newVal) {
        Item item = employeeService.searcItem(newVal);
        txtItemDescription.setText(item.getItemDescription());
        txtStock.setText(item.getItemQty().toString());
        txtUnitPriceOnOrder.setText(String.valueOf(item.getItemPrice()));
    }

}
