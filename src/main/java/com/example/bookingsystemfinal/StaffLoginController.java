package com.example.bookingsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StaffLoginController {
    @FXML
    private PasswordField staffPasswordtxt;

    @FXML
    private TextField staffEmailtxt;

    public void staffLogin (ActionEvent event) {
        if (staffEmailtxt.getText().equals("staff") && staffPasswordtxt.getText().equals("staffpass")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("staff-menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}