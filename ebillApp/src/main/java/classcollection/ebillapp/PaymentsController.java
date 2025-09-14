package classcollection.ebillapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;

public class PaymentsController {

    private LinkedList<TransactionHistory> currenRecord;
    Customer currentCustomer;
    DynamicArray balance;

    @FXML
    private Label amount;

    @FXML
    private Label amountDue;

    @FXML
    private Label billStatus;

    @FXML
    private Label consumption;

    @FXML
    private Label dueDate;

    @FXML
    private TextField payment;

    @FXML
    private Label status;

    private Stage stage;
    private Scene scene;


    public void setCustomerDetails(Customer customer) {
        this.currentCustomer = customer;
    }

    public void setCurrentTransaction(LinkedList<TransactionHistory> currentTransaction) {
        this.currenRecord = currentTransaction;
    }

    public void initialize() {
        balance = new DynamicArray();
        generateBillAmount();
        generateRandomBillStatus();
        generateRandomDueDate();
        calculateAndDisplayConsumption();
    }

    private void generateBillAmount() {
        int bill = generateRandomNumber(2500, 10000);
        amount.setText("₱" + bill + ".00");
        amountDue.setText("₱" + bill + ".00");
    }

    private void generateRandomBillStatus() {
        String[] statuses = {
                "Due soon. Please pay on time!",
                "Overdue. Immediate payment required.",
                "Thank you for your prompt payment.",
                "Payment pending. Kindly pay by the due date.",
                "Bill generated. Awaiting payment."
        };
        int randomIndex = generateRandomNumber(0, statuses.length - 1);
        billStatus.setText(statuses[randomIndex]);
    }

    private void generateRandomDueDate() {
        LocalDate today = LocalDate.now();
        int randomDays = generateRandomNumber(1, 30);
        LocalDate due = today.plusDays(randomDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dueDate.setText(due.format(formatter));
    }

    private void calculateAndDisplayConsumption() {
        int wattage = generateRandomNumber(500, 3000);
        int hoursUsedPerDay = generateRandomNumber(1, 24);

        double dailyConsumption = (wattage * hoursUsedPerDay) / 1000.0;
        consumption.setText(String.format("%.2f kWh (Wattage: %dW, Hours: %d)", dailyConsumption, wattage, hoursUsedPerDay));
    }

    @FXML
    private void processPayment() {
        try {
            int paymentAmount = Integer.parseInt(payment.getText().trim());
            balance.add(paymentAmount);
            int totalPaid = calculateTotalPaid();
            int billAmount = parseAmount(amount.getText().trim());
            int remainingAmount = billAmount - totalPaid;

            if (remainingAmount > 0) {
                status.setText("Remaining Amount: ₱" + remainingAmount + ".00");
                amountDue.setText("₱" + remainingAmount + ".00");
            } else {
                status.setText("Payment Complete");
                amountDue.setText("₱0.00");
                balance = new DynamicArray();
            }
        } catch (NumberFormatException e) {
            status.setText("Invalid Input");
        }
    }

    private int calculateTotalPaid() {
        int total = 0;
        for (int i = 0; i < balance.count; i++) {
            total += balance.array[i];
        }
        return total;
    }

    private int parseAmount(String amountText) {
        String sanitizedAmount = amountText.replace("₱", "").replace(".00", "");
        return Integer.parseInt(sanitizedAmount);
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @FXML
    void ReturnButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root2 = loader.load();

        MainMenuController controller = loader.getController();
        controller.setCurrentCustomer(currentCustomer);
        controller.setCurrentTransaction(currenRecord);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();

    }
}
