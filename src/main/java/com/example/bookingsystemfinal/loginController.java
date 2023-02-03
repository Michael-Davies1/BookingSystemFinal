package com.example.bookingsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginController {
    @FXML
    private Label StatusLBL;

    @FXML
    private TextField passwordtxt;

    @FXML
    private TextField emailstxt;

    public boolean login(ActionEvent event) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\userInformation.accdb";
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM memberInformation";
            ResultSet RS = statement.executeQuery(sql);
            while (RS.next()) {
                if ((RS.getString("memberEmail").equals(emailstxt.getText()))) {
                    if (RS.getString("memberPassword").equals(passwordtxt.getText())) {
                        StatusLBL.setText("welcome member");
                        connection.close();
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("member-menu.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));
                            stage.show();
                            ((Node) (event.getSource())).getScene().getWindow().hide();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    } else {
                        StatusLBL.setText("password invalid");
                    }
                } else {
                    StatusLBL.setText("email address is invalid");
                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("error in the sql statement" + e);
        }
        return false;
    }
}