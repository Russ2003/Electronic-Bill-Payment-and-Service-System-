package classcollection.ebillapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import javafx.event.ActionEvent;



public class TransactionController {

    @FXML
    private BorderPane borderPane;

    private Customer currentCustomer;

    private LinkedList<TransactionHistory> currentRecord;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {

    }

    public void setCustomerDetails(Customer customer) {
        this.currentCustomer = customer;
    }

    public void setTranssactionDetails(LinkedList<TransactionHistory> transaction) {
        this.currentRecord = transaction;
    }


    @FXML
    private void ViewInfo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/classcollection/ebillapp/TransactionInfo.fxml"));
            Parent view = loader.load();

            TransactionRecordsDisplay controller = loader.getController();
            controller.setCustomerDetails(currentCustomer);
            controller.setTransactionDetails(currentRecord);
            borderPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("TransactionInfo.fxml", e);
        }
    }

    private void showErrorAlert(String fileName, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Failed to Load FXML");
        alert.setContentText("Could not load " + fileName + ".\n" + e.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void Return(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root2 = loader.load();

        MainMenuController controller = loader.getController();
        controller.setCurrentCustomer(currentCustomer);
        controller.setCurrentTransaction(currentRecord);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
    }

}
