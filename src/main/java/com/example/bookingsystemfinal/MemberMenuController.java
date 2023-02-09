package com.example.bookingsystemfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MemberMenuController {
    @FXML
    private TextField searchBar;
    @FXML
    private ListView<String> listDisplay;

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
                if (RS.getString("className").equalsIgnoreCase(searchBar.getText())) {
                    listDisplay.getItems().clear();
                    listDisplay.getItems().addAll(RS.getString("className"));


                }

            }


        } catch (Exception e) {
            System.out.println("error in the sql statement" + e);
        }
    }

    public void displayList(ActionEvent event) {

        try {
            FXMLLoader newfxmlLoader = new FXMLLoader(getClass().getResource("session-menu.fxml"));
            Parent root1 = (Parent) newfxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                    System.out.println(RS);
                } else {
                    break;
                }
            }
            connection.close();
            mergeSort(points);
        } catch (Exception e) {
            System.out.println("error in the sql statement" + e);
        }

    }

    private static void mergeSort(ArrayList<Integer> inputArray) {
        int inputlength = inputArray.size();
        if (inputlength < 2) {
            return;
        }
        int midIndex = inputlength / 2;
        ArrayList<Integer> leftHalf = new ArrayList<>(midIndex);
        ArrayList<Integer> rightHalf = new ArrayList<>(inputlength - midIndex);
        for (int i = 0; i < midIndex; i++) {
            leftHalf.set(i, inputArray.get(i));
        }
        for (int i = midIndex; i < inputlength; i++) {
            rightHalf.set(i - midIndex, inputArray.get(i));
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(inputArray, leftHalf, rightHalf);

    }

    private static void merge(ArrayList<Integer> inputArray, ArrayList<Integer> leftHalf, ArrayList<Integer> rightHalf) {
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (leftHalf.get(i) <= rightHalf.get(j)) {
                inputArray.set(k, leftHalf.get(i));
                i++;
            } else {
                inputArray.set(k, rightHalf.get(j));
                j++;
            }
            k++;
        }
        while (i < leftSize) {
            inputArray.set(k, leftHalf.get(i));
            i++;
            k++;
        }
        while (j < rightSize) {
            inputArray.set(k, rightHalf.get(j));
            j++;
            k++;
        }
    }
}