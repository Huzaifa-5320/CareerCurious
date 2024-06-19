package com.jobportal.controller;

import com.jobportal.model.UserSession;
import com.jobportal.services.PostService;
import com.jobportal.utils.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Locale;

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
        // Extracting text from fields
        String jobTitle = title.getText();
        String jobCompanyName = companyName.getText();
        String jobLocation = region.getText();
        String jobPay = pay.getText();
        String jobDescription = description.getText();

        // Checking if any field is empty
        if (jobTitle.isEmpty() || jobCompanyName.isEmpty() || jobLocation.isEmpty() || jobPay.isEmpty() || jobDescription.isEmpty()) {
            errormsg.setText("Please fill in all fields.");
            return;
        }

        // Check that job title has a valid length
        if (jobTitle.length() < 3) {
            errormsg.setText("Title length should be at least three characters.");
            return;
        }

        // Check that company name has a valid length
        if (jobCompanyName.length() < 3) {
            errormsg.setText("Company name should be at least three characters long.");
            return;
        }

        // Checking that description has a valid length
        String[] jobDescArr = jobDescription.split(" ");
        if (jobDescArr.length < 50) {
            errormsg.setText("Job description should be at least 50 words.");
            return;
        }
        else if (jobDescArr.length > 190) {
            errormsg.setText("Job description cannot be more than 190 words.");
            return;
        }

        // Checking that entered pay is a valid integer
        try {
            Integer.parseInt(jobPay);
        } catch (NumberFormatException e) {
            errormsg.setText("Pay should be an integer number.");
            return;
        }

        // Checking that entered country name is valid
        if (!isCountryName(jobLocation)) {
            errormsg.setText("Entered country name does not exist.");
            return;
        }

        // If all checks pass, proceed with posting the job
        errormsg.setText("");

        //created a post object
        com.jobportal.model.Post post = new com.jobportal.model.Post(jobTitle, jobCompanyName, jobLocation, jobPay, UserSession.getInstance().getEmail(), jobDescription);

        PostService service = new PostService();
        if (!service.postJob(post)) {
            errormsg.setText("Failed to post the job. Some error occured");
            return;
        }
        //Navigating to Home page if the job is posted successfully
        Navigation.navigateTo(event, "/com/jobportal/Home.fxml");
    }

    // Method for checking if entered country's name is valid or not
    private static boolean isCountryName(String name) {
        for (String isoCountry : Locale.getISOCountries()) {
            Locale locale = new Locale("", isoCountry);
            if (locale.getDisplayCountry().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
