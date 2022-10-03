package com.example.bookingsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
    @FXML
    private TextField signUpEmailtxt;
    @FXML
    private TextField ConfirmEmailtxt;
    @FXML
    private PasswordField signUpPassword;
    @FXML
    private PasswordField ConfirmPassword;
    @FXML
    private Label SignUpStatus;

    public void signUpbtn (ActionEvent event) {
        if (signUpEmailtxt.getText().equals("p") && ConfirmEmailtxt.getText().equals("p") && signUpPassword.getText().equals("p") && ConfirmPassword.getText().equals("p")) {
            SignUpStatus.setText("Sign-Up Successful");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("member-menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            SignUpStatus.setText("Emails or Passwords do not match. please re-enter details:");
        }

    }
}