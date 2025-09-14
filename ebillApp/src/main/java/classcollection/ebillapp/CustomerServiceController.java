package classcollection.ebillapp;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class CustomerServiceController {

    PriorityQueue<Integer> pq = new PriorityQueue<>();


    @FXML
    private Button GenerateTicketButton;

    @FXML
    private Button ReturnButton;

    @FXML
    private TextArea textArea;

    @FXML
    private Label ticketNumber;

    private Stage stage;
    private Scene scene;

    Customer currentCustomer;
    LinkedList<TransactionHistory> currenRecord;

    @FXML
    public void initialize() {
        generatePresetQueue();
    }

    public void setCustomerDetails(Customer customer) {
        this.currentCustomer = customer;
    }

    @FXML
    void generateTicket(ActionEvent event) {
        GenerateTicketButton.setDisable(true);
        ReturnButton.setDisable(true);
        int randomNum = (int) (Math.random() * 101);  // 0 to 100
        List<String> messages = new ArrayList<>();
        messages.add("Your ticket number is: " + randomNum);
        messages.add("Waiting in line....");
        ticketNumber.setText(String.valueOf(randomNum));
        pq.add(randomNum);
        messages.add("Queue: " + pq);

        displayMessagesSequentially(messages, 0, this::updateStatus);
    }

    private void updateStatus() {
        if (!pq.isEmpty()) {
            processQueueUntilUserNumber();
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("Queue is empty");
            messages.add("No tickets in line.");

            displayMessagesSequentially(messages, 0, null);
        }
    }

    private void displayMessagesSequentially(List<String> messages, int index, Runnable onFinish) {
        if (index < messages.size()) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                textArea.appendText(messages.get(index) + "\n");
                displayMessagesSequentially(messages, index + 1, onFinish);
            });
            pause.play();
        } else {
            if (onFinish != null) {
                onFinish.run();
            }
        }
    }

    private void generatePresetQueue() {
        for (int i = 0; i < 10; i++) {
            int randomNum = (int) (Math.random() * 101);
            pq.add(randomNum);
        }
    }

    @FXML
    void returnButton(ActionEvent event) throws IOException {
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

    private void processQueueUntilUserNumber() {
        if (!pq.isEmpty()) {
            int nextTicket = pq.poll();
            List<String> messages = new ArrayList<>();
            messages.add("Next ticket: " + nextTicket);
            messages.add("Current queue: " + pq);

            displayMessagesSequentially(messages, 0, () -> {
                if (nextTicket == Integer.parseInt(ticketNumber.getText())) {
                    List<String> finalMessage = new ArrayList<>();
                    finalMessage.add("\nYou are now speaking to one of the service.");
                    displayMessagesSequentially(finalMessage, 0, null);
                    ReturnButton.setDisable(false);
                    GenerateTicketButton.setDisable(false);
                } else {
                    processQueueUntilUserNumber();
                }
            });
        }
    }

    public void setCurrentTransaction(LinkedList<TransactionHistory> currentTransaction) {
        this.currenRecord = currentTransaction;
    }
}
