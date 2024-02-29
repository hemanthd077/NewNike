package com.example.urbanship;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static java.lang.System.out;

@WebServlet(name = "collection", value = "/collection")
public class collection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<List<String>> productData = new ArrayList<>();
        //Uploading data to database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String URL = "jdbc:mysql://localhost:3306/nike";
        String USERNAME = "root";
        String PASSWORD="Hemanth@123";
        // Establish a connection
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
            String sql = "SELECT * FROM nikeProducts";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<String> temp;
                    while (resultSet.next()) {
                        // Retrieve data from the result set
                        temp = new ArrayList<>();
                        temp.add(resultSet.getString(2));
                        temp.add(resultSet.getString(3));
                        temp.add(resultSet.getString(4));
                        temp.add(resultSet.getString(5));
                        productData.add(temp);
                    }
                    request.setAttribute("productData", productData);
                }
            }
            request.getRequestDispatcher("collection.jsp").forward(request,response);
        }
        catch (Exception e){
            out.println(e);
        }
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//    }
}