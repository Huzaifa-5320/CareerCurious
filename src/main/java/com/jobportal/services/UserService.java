package com.jobportal.services;

import com.jobportal.model.User;
import com.jobportal.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    public boolean registerUser(User user) {

        //Query to register a new user
        String query = "INSERT INTO USERS(firstName,lastName,email,password) VALUES (?,?,?,?,?)";

        //connection to database
        try (Connection connection = DatabaseConnection.getConnection()) {

            //creating object for sending parameterized SQL statements to the database.
            PreparedStatement statement = connection.prepareStatement(query);

            //Setting values
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword()
            );

            //Exceuting query
            int affectedRows = statement.executeUpdate();

            //check if row inserted successfully
            return affectedRows>0 ;

        } catch (SQLException e) {
            System.out.println("Exception occured while registering user");
            e.printStackTrace();
            return false;
        }
    }
}
