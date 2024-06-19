package com.jobportal.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    public static void navigateTo(ActionEvent event, String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource(fxmlPath));
        try {
            Parent root = loader.load();
            // Get the stage and set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to navigate to Home");
        }
    }
}
