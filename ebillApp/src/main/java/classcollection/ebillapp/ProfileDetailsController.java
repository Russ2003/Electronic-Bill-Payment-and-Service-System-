package classcollection.ebillapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class ProfileDetailsController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TreeView<String> profileTreeView;

    private Customer currentCustomer;
    private LinkedList<TransactionHistory> currenRecord;

    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {
        initializeTreeView();

    }

    public void setCustomerDetails(Customer customer) {
        this.currentCustomer = customer;
        initializeTreeView();
    }
    public void setCurrentRecord(LinkedList<TransactionHistory> record) {
        this.currenRecord = record;
    }

    private void initializeTreeView() {
        TreeItem<String> rootItem = new TreeItem<>("Customer Profile");
        rootItem.setExpanded(true);

        TreeItem<String> billingInfoItem = new TreeItem<>("Sample Information");
        TreeItem<String> usageDataItem = new TreeItem<>("Sample Information");
        TreeItem<String> basicInformation = new TreeItem<>("Basic Information");

        rootItem.getChildren().addAll(billingInfoItem, usageDataItem, basicInformation);

        profileTreeView.setRoot(rootItem);
    }

    @FXML
    void selectItem(MouseEvent event) throws IOException {
        TreeItem<String> item = profileTreeView.getSelectionModel().getSelectedItem();
        if (item != null && "Basic Information".equals(item.getValue())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BasicInformation.fxml"));
            Parent view = loader.load();

            BasicInformationController controller = loader.getController();
            controller.setCustomer(currentCustomer);
            borderPane.setCenter(view);
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
}
