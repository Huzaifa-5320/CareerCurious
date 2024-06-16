package com.jobportal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {

    @FXML
    private TextField searchField;

    @FXML
    void jobPostForm(ActionEvent event) {
        try {
            // Navigate to Post page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jobportal/Post.fxml"));
            Parent root = loader.load();
            // Get the stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Log the exception (use a proper logging framework in a real application)
            System.out.println("Exception occurred while loading Post.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    void search(ActionEvent event) {
        // Your search logic here
    }
}
