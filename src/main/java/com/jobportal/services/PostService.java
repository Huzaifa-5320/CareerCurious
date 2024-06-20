package com.jobportal.services;

import com.jobportal.model.Post;
import com.jobportal.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostService {

    private static final String ADD_POST_QUERY = "INSERT INTO POST(title,companyName,location,pay,email,description) VALUES(?,?,?,?,?,?)";
    private static final String GET_POST_QUERY = "SELECT * FROM Post";

    public boolean postJob(Post post) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_POST_QUERY);

            //setting parameter's value
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getCompanyName());
            statement.setString(3, post.getLocation());
            statement.setString(4, post.getPay());
            statement.setString(5, post.getEmail());
            statement.setString(6, post.getDescription());

            //return's true if affected rows greater than zero
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("SQL ERROR: \n");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_POST_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String companyName = resultSet.getString("companyName");
                String location = resultSet.getString("location");
                String pay = resultSet.getString("pay");
                String email = resultSet.getString("email");
                String description = resultSet.getString("description");

                // Create a Post object and add it to the list
                Post post = new Post(title, companyName, location, pay, email, description);
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println("SQL ERROR: \n");
            e.printStackTrace();
        }
        return posts;
    }
}
