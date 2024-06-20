package com.jobportal.controller;

import com.jobportal.model.Post;
import com.jobportal.services.PostService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private VBox postsContainer;

    private PostService postService;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postService = new PostService();
        populatePosts();
    }

    private void populatePosts() {
        //Fetching posts from database
        ArrayList<com.jobportal.model.Post> posts = postService.getPosts();
        //Adding all the posts to the post container
        for (com.jobportal.model.Post post : posts) {
            //creating a pane for post
            AnchorPane postPane = createPostPane(post);
            postsContainer.getChildren().add(postPane);
        }
    }

    private AnchorPane createPostPane(Post post) {
        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(241.0);
        pane.setPrefWidth(560.0);
        pane.setStyle("-fx-border-width: 1px; -fx-border-color: gray; -fx-border-radius: 10px; -fx-padding: 10px");

        //Label for title
        Label titleLabel = new Label(post.getTitle());
        titleLabel.setLayoutX(21.0);
        titleLabel.setLayoutY(38.0);
        titleLabel.setFont(new javafx.scene.text.Font("System Bold", 14.0));

        //Label for company name
        Label companyLabel = new Label(post.getCompanyName());
        companyLabel.setLayoutX(21.0);
        companyLabel.setLayoutY(65.0);

        //Label for Location
        Label locationLabel = new Label(post.getLocation());
        locationLabel.setLayoutX(21.0);
        locationLabel.setLayoutY(80.0);

        //Label for description
        Label descriptionLabel = new Label(post.getDescription());
        descriptionLabel.setLayoutX(14.0);
        descriptionLabel.setLayoutY(95.0);
        descriptionLabel.setPrefHeight(230.0);
        descriptionLabel.setPrefWidth(550.0);
        descriptionLabel.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        descriptionLabel.setTextFill(javafx.scene.paint.Color.valueOf("#8d8d8d"));
        descriptionLabel.setWrapText(true);

        //Button to apply to job
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(80.0);
        applyButton.setPrefHeight(30.0);
        applyButton.setLayoutX(14.0);
        applyButton.setLayoutY(330.0);
        applyButton.setStyle("-fx-background-color: #071091; -fx-text-fill: white;");

        //Adding all the children to pane
        pane.getChildren().addAll(titleLabel, companyLabel, locationLabel, descriptionLabel, applyButton);

        return pane;
    }
}
