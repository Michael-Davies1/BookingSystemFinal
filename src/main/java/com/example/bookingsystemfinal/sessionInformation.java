package com.example.bookingsystemfinal;

import java.sql.*;
import java.util.ArrayList;

public class sessionInformation {
    private String className;
    private String classRoom;
    private int maxCapacity;
    private String recAge;
    private String gender;
    private double length;
    private String classDate;
    private String roomType;

    public sessionInformation(String className, String classRoom) {
        this.className = className;
        this.classRoom = classRoom;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
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

    public static ArrayList<String> sessionName = new ArrayList<>();
    public static ArrayList<Integer> sessionRoom = new ArrayList<>();

    public static void databaseclass() {
        try {
            if (sessionName == null && sessionRoom == null) {
                String dataLoc = System.getProperty("user.dir") + "\\src//main//resources//DataBase//userInformation.accdb";
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + dataLoc, "", "");
                Statement statement = connection.createStatement();
                ResultSet rName = statement.executeQuery("select className from classinformation");
                ResultSet rRoom = statement.executeQuery("select classRoom from classinformation");
                while (true) {
                    if (rName.next()) {
                        sessionName.add(rName.getString("Email"));
                    } else {
                        break;
                    }
                }
                while (true) {
                    if (rRoom.next()) {
                        sessionRoom.add(rRoom.getInt("Password"));
                    } else {
                        break;
                    }
                }
                System.out.println((sessionName));
                System.out.println(sessionRoom);
                connection.close();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
