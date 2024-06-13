package com.jobportal.controller;

import com.jobportal.model.User;
import com.jobportal.services.UserService;
import com.jobportal.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Signup {

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    void signUpBtn(ActionEvent event) {

        //extracting text from  fields
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();

        if (!(Validation.name(firstname) && Validation.name(lastname) && Validation.isEmail(userEmail) && Validation.password(userPassword))) {
            return;
        }

        //creating user object
        User user = new User(firstname, lastname, userEmail, userPassword);

        //Performing db operation
        UserService service = new UserService();
        boolean registered = service.registerUser(user);

        //check for registration
        if (registered) {
            System.out.println("User has been registered");
        }
        else {
            System.out.println("Failed to register user");
        }

    }

    @FXML
    void navigateToLogin(ActionEvent event) throws IOException {
        //navigate to SignUp page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jobportal/Login.fxml"));
        Parent root = loader.load();
        // Get the stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
