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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class MainMenuController {
    private HashMap<String, Customer> userDatabase;
    private Customer currentCustomer;

    private LinkedList<TransactionHistory> currentTransaction;

    private CustomerTree customerTree;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setUserDatabase(HashMap<String, Customer> userDatabase) {
        this.userDatabase = userDatabase;
        customerTree = new CustomerTree();
        userDatabase.values().forEach(customerTree::insert);
    }

    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }

    public void setCurrentTransaction(LinkedList<TransactionHistory> records) {
        this.currentTransaction = records;
    }


    @FXML
    void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileDetails.fxml"));
        root = loader.load();

        ProfileDetailsController profileController = loader.getController();
        profileController.setCustomerDetails(currentCustomer);
        profileController.setCurrentRecord(currentTransaction);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void exitButton(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void billsandpaymentsButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BillsandPayment.fxml"));
        root = loader.load();

        PaymentsController controller = loader.getController();
        controller.setCustomerDetails(currentCustomer);
        controller.setCurrentTransaction(currentTransaction);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void customerserviceButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerService.fxml"));
        Parent root2 = loader.load();

        CustomerServiceController controller = loader.getController();
        controller.setCustomerDetails(currentCustomer);
        controller.setCurrentTransaction(currentTransaction);


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void transactionButton(ActionEvent event) {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction2.fxml"));
                root = loader.load();

                TransactionController Controller = loader.getController();
                Controller.setTranssactionDetails(currentTransaction);
                Controller.setCustomerDetails(currentCustomer);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        });
        pause.play();
    }
}
