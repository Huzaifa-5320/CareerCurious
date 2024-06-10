package com.jobportal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private TextField namefield;

    @FXML
    void btnClick(ActionEvent event) {
        Stage mainWindow = (Stage) namefield.getScene().getWindow() ;
        String title = namefield.getText();
        mainWindow.setTitle(title);
    }

}