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
    private TextField region;

    @FXML
    private TextField pay;

    @FXML
    private TextField title;

    @FXML
    void postJob(ActionEvent event) {

        String jobTitle = title.getText();
        String jobCompanyName = companyName.getText();
        String jobLocation = region.getText();
        String jobPay = pay.getText();
        String jobDescription = description.getText();


        if (jobTitle.isEmpty() || jobCompanyName.isEmpty() || jobLocation.isEmpty() || jobPay.isEmpty() || jobDescription.isEmpty()) {
            errormsg.setText("Please fill in all fields.");
        }
        else {
            errormsg.setText("");
        }
    }
}
