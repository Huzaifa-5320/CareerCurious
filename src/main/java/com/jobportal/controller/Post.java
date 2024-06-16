package com.jobportal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Post {

    @FXML
    private TextField companyName;

    @FXML
    private TextArea description;

    @FXML
    private Label errormsg;

    @FXML
    private TextField location;

    @FXML
    private TextField pay;

    @FXML
    private TextField title;

    @FXML
    void postJob(ActionEvent event) {

    }

}
