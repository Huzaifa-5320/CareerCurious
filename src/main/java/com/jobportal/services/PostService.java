package com.jobportal.services;

import com.jobportal.model.Post;
import com.jobportal.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostService {

    private static final String POST_QUERY = "INSERT INTO POST(title,companyName,location,pay,email,description) VALUES(?,?,?,?,?,?)";

    public boolean postJob(Post post) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(POST_QUERY);

            //setting parameter's value
            statement.setString(1,post.getTitle());
            statement.setString(2,post.getCompanyName());
            statement.setString(3,post.getLocation());
            statement.setString(4,post.getPay());
            statement.setString(5,post.getEmail());
            statement.setString(6,post.getDescription());

            //return's true if affected rows greater than zero
            int affectedRows = statement.executeUpdate() ;
            return affectedRows>0 ;

        } catch (SQLException e) {
            System.out.println("SQL ERROR: \n");
            e.printStackTrace();
            return false;
        }
    }
}
