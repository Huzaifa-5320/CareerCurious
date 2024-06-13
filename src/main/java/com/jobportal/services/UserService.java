package com.jobportal.services;

import com.jobportal.model.User;
import com.jobportal.utils.DatabaseConnection;
import com.jobportal.utils.PasswordHash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // SQL queries for registering a user and verifying email
    private static final String REGISTER_USER_QUERY = "INSERT INTO USERS(firstName, lastName, email, password) VALUES (?, ?, ?, ?)";
    private static final String VERIFY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";

    /**
     * Registers a new user in the database.
     */
    public boolean registerUser(User user) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(REGISTER_USER_QUERY)) {

            // Setting parameter values in the prepared statement
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());

            // Executing the query and checking if a row was affected
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Exception occurred while registering user");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a user from the database based on the provided email.
     */
    public User getUserByEmail(String email) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(VERIFY_EMAIL_QUERY)) {

            // Setting the email parameter in the prepared statement
            statement.setString(1, email);

            // Executing the query and processing the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Creating and returning a User object from the result set
                    return new User(
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("email"),
                            resultSet.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred while verifying email");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the provided plain password matches the hashed password.
     */
    public boolean checkPassword(String hashedPassword, String password) {
        return PasswordHash.verifyPassword(password, hashedPassword);
    }

    /**
     * Verifies the user's email and password for login.
     */
    public boolean loginVerification(String email, String password) {
        // Retrieving the user by email
        User user = getUserByEmail(email);
        if (user == null) {
            return false;
        }
        // Checking if the provided password matches the hashed password
        return checkPassword(user.getPassword(), password);
    }
}
