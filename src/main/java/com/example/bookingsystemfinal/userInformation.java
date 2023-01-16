package com.example.bookingsystemfinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class userInformation {
    private String userEmail;

    private String userPassword;

    public userInformation(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testspring", "root", "password");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public static ArrayList<String> memberEmail = new ArrayList<>();
    public static ArrayList<String> memberPassword = new ArrayList<>();

    public static void databaseMember() {
        try {
            if (memberEmail == null && memberPassword == null) {
                String dataLoc = System.getProperty("user.dir") + "\\src//main//resources//DataBase//userInformation.accdb";
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + dataLoc, "", "");
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rEmail = statement.executeQuery("select Email from memberInformation");
                ResultSet rPassword = statement.executeQuery("select Password from memberInformation");
                while (true) {
                    if (rEmail.next()) {
                        memberEmail.add(rEmail.getString("Email"));
                    } else {
                        break;
                    }
                }
                while (true) {
                    if (rPassword.next()) {
                        memberPassword.add(rPassword.getString("Password"));
                    } else {
                        break;
                    }
                }
                System.out.println((memberEmail));
                System.out.println(memberPassword);
                connection.close();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}

