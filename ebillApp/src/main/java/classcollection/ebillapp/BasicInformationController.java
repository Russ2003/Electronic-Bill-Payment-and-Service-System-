package classcollection.ebillapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BasicInformationController {

    private Customer selectedCustomer;
    @FXML
    private Label addressLabel;

    @FXML
    private Label customerID;

    @FXML
    private Label customerName;

    @FXML
    private Label customerPassword;

    @FXML
    private Label customerUsername;


    private Customer customer;

    public void setCustomer(Customer customer){
        this.customer = customer;
        initData();
    }

    public void initData(){
        selectedCustomer = customer;
        customerName.setText(customer.getFullName());
        customerUsername.setText(customer.getUsername());
        customerPassword.setText(customer.getPassword());
        customerID.setText(String.valueOf(customer.getCustomerId()));
        addressLabel.setText(customer.getAddress());

    }

}
