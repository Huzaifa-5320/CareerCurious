package com.jobportal.controller;

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
        System.out.println(email.getText());
        System.out.println(firstName.getText());
        System.out.println(lastName.getText());
        System.out.println(password.getText());
    }

    @FXML
    void navigateToLogin(ActionEvent event)throws IOException {
        //navigate to SignUp page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jobportal/Login.fxml")) ;
        Parent root = loader.load();
        // Get the stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
