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
import javafx.scene.control.Label;
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
    private Label errormsg;


    @FXML
    void signUpBtn(ActionEvent event) {

        //extracting text from  fields
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();

        //Validating fields and displaying custom error messages

        if (!(Validation.name(firstname) && Validation.name(lastname))) {
            errormsg.setText("Name length should be between 3 to 10");
            return;
        }
        else if (!Validation.isEmail(userEmail)) {
            errormsg.setText("Email isn't is valid");
            return;
        }
        else if (!Validation.password(userPassword)) {
            errormsg.setText("Password length should be between 8 to 16");
            return;
        }

        //creating user object
        User user = new User(firstname, lastname, userEmail, userPassword);

        //Performing db operation
        UserService service = new UserService();
        boolean registered = service.registerUser(user);

        //clearing textfields
        firstName.clear();
        lastName.clear();
        email.clear();
        password.clear();

        //check for registration
        if (registered) {
            errormsg.setText("");
            System.out.println("User has been registered");
        }
        else {
            System.out.println("Failed to register user");
            errormsg.setText("Failed to register user");
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
