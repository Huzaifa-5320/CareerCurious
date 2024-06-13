package com.jobportal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/careercurious";
    private static final String USER = "root";
    private static final String PASSWORD = "Anas3434@";

    public static Connection getConnection() throws SQLException {
        try {
            // Ensure the MySQL driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error in connecting driver");
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        }
    }
}
