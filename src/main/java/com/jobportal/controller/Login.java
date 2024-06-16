package com.jobportal.controller;

import com.jobportal.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label errormsg;

    @FXML
    void navigateToSignUp(ActionEvent event) throws IOException {
        // Navigate to SignUp page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jobportal/Signup.fxml"));
        Parent root = loader.load();
        // Get the stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void navigateToHome(ActionEvent event) throws IOException {
        // Navigate to Home
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jobportal/Home.fxml"));
        Parent root = loader.load();
        // Get the stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginBtn(ActionEvent event) {
        // Extracting text
        String userEmail = email.getText();
        String userPassword = password.getText();

        // Creating object for services
        UserService service = new UserService();

        // Checking for valid credentials
        if (!service.loginVerification(userEmail, userPassword)) {
            // Setting error message
            errormsg.setText("* Invalid Credentials");
            // Clearing fields
            email.clear();
            password.clear();
            return;
        }
        errormsg.setText("");
        try {
            navigateToHome(event);
        } catch (IOException e) {
            System.out.println("Exception occurred while loading home page");
            e.printStackTrace();
        }
    }
}
