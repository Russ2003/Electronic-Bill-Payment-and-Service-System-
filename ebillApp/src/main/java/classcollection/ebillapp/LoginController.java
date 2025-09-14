package classcollection.ebillapp;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class LoginController {

    private HashMap<String, Customer> userDatabase = new HashMap<>();

    private LinkedList<TransactionHistory> records = new LinkedList<>();


    @FXML
    private TextField Name;

    @FXML
    private PasswordField Password;

    @FXML
    private Label loginStatus;

    @FXML
    public void initialize() {
        readcustomerData();

    }


    @FXML
    void loginButton(ActionEvent event) {
        String username = Name.getText();
        String password = Password.getText();

        if (userDatabase.containsKey(username)) {
            if (userDatabase.get(username).getPassword().equals(password)) {
                Customer loggedInCustomer = userDatabase.get(username);
                loginStatus.setVisible(true);
                loginStatus.setText("Login Successfully");

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(e -> {
                    try {
                        // Debugging information
                        System.out.println("Loading MainMenu.fxml...");
                        System.out.println("Logged in customer: " + loggedInCustomer.getUsername());
                        System.out.println("User database size: " + userDatabase.size());
                        System.out.println("Records size: " + records.size());

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                        Parent root = loader.load();
                        MainMenuController mainMenuController = loader.getController();
                        mainMenuController.setCurrentCustomer(loggedInCustomer);
                        mainMenuController.setUserDatabase(userDatabase);
                        mainMenuController.setCurrentTransaction(records);

                        Stage stage = (Stage) loginStatus.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException ex) {
                        System.err.println("Failed to load MainMenu.fxml: " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }
                });
                pause.play();

            } else {
                loginStatus.setVisible(true);
                loginStatus.setText("Login Failed. Try Again!");
                Name.clear();
                Password.clear();
            }
        } else {
            loginStatus.setVisible(true);
            loginStatus.setText("Account not existed");
        }
    }



    private void readcustomerData() {
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("Customer.txt");
            if (file == null) {
                throw new FileNotFoundException("File not found: " + "Customer.txt");
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                String fullName = data[0];
                String userName = data[1];
                String password = data[2];
                int customerId = Integer.parseInt((data[3]));
                String customerAddress = data[4];

                int month = Integer.parseInt(data[5]);
                String duedate = data[6];
                int consumption = Integer.parseInt(data[7]);
                double amount = Double.parseDouble(data[8]);
                double amountDue = Double.parseDouble(data[9]);
                String status = data[10];

                // Create a Customer object and add it to your collection
                Customer profile = new Customer(fullName, userName, password, customerId, customerAddress);
                addTransaction(new TransactionHistory(month, duedate, consumption, amount, amountDue, status));
                userDatabase.put(profile.getUsername(), profile);


            }
            fileScanner.close();
            System.out.println("File read successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + "Customer.txt.txt");
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    public void addTransaction(TransactionHistory transactionHistory){
        records.add(transactionHistory);
    }
}
