package com.example.bookingsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class StaffMenuController {
    @FXML
    private Button sortButton;
    public static void sortMethod(ActionEvent event) {
        ArrayList<Integer> points = new ArrayList<Integer>();

        String DatabaseLocation = System.getProperty("user.dir") + "\\userInformation.accdb";
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT gymPoints FROM memberInformation";
            ResultSet RS = statement.executeQuery(sql);
            while (true) {
                if (RS.next()) {
                    points.add(RS.getInt("gymPoints"));
                    System.out.println(points);
                }
            }
        } catch (Exception e) {
            System.out.println("error in the sql statement" + e);
        }
    }
}
