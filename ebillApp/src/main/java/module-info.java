module classcollection.ebillapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens classcollection.ebillapp to javafx.fxml;
    exports classcollection.ebillapp;
}