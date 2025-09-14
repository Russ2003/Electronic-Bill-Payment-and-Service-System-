package classcollection.ebillapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.LinkedList;

public class TransactionRecordsDisplay {

    @FXML
    private Label Amount;

    @FXML
    private Label Consumption;

    @FXML
    private Label Monthly;

    @FXML
    private Label amountDue;

    @FXML
    private Label dueDate;

    @FXML
    private Label paidStatus;

    LinkedList<TransactionHistory> records = new LinkedList<>();


    Customer currentCustomer;

    public void setCustomerDetails(Customer customer) {
        this.currentCustomer = customer;
    }

    public void setTransactionDetails(LinkedList<TransactionHistory> currentRecord) {
        this.records = currentRecord;
        initData();

    }

    public void initialize(){


    }
    public void initData() {
        if (!records.isEmpty()) {
            TransactionHistory details = records.get(0);

            Amount.setText(String.valueOf(details.getAmount()));
            Consumption.setText(String.valueOf(details.getConsumption()));
            Monthly.setText(String.valueOf(details.getMonth()));
            amountDue.setText(String.valueOf(details.getAmountDue()));
            dueDate.setText(String.valueOf(details.getDueDate()));
            paidStatus.setText(details.getStatus());
        } else {
            Amount.setText("No data available");
            Consumption.setText("No data available");
            Monthly.setText("No data available");
            amountDue.setText("No data available");
            dueDate.setText("No data available");
            paidStatus.setText("No data available");
        }
    }

}
