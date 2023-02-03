package com.example.bookingsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MemberMenuController {
    ArrayList<String> classes = new ArrayList<>(Arrays.asList());

    @FXML
    private TextField searchBar;
    public void bookedSessionsButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("booked-sessions-tab.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(ActionEvent event) {
        String DatabaseLocation = System.getProperty("user.dir") + "\\userInformation.accdb";
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM classInformation";
            ResultSet RS = statement.executeQuery(sql);
            while (RS.next()) {
                if (RS.getString("className").contains(searchBar.getText())){
                    System.out.println("className");

                }

            }


        } catch (Exception e) {
            System.out.println("error in the sql statement" + e);
        }
    }
}