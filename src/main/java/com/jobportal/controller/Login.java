package com.jobportal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    void loginBtn(ActionEvent event) {

        String userEmail = email.getText();
        String userPassword = password.getText();
        System.out.println("User Email:" + userEmail);
        System.out.println("Password:" + userPassword);
        email.clear();
        password.clear();

    }

}