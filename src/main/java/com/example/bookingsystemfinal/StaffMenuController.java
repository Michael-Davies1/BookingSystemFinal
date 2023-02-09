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

