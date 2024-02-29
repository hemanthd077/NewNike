package com.example.urbanship;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "signup", value = "/signup")
public class signup extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String Password = request.getParameter("userPassword");
        String ConfirmPassword = request.getParameter("UserConfirmPassword");

        if(Password.equals(ConfirmPassword)){

            String userName = request.getParameter("userName");
            String userEmail = request.getParameter("userEmailid");
            String userPassword = request.getParameter("userPassword");

            //Connect db to store user data
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String URL = "jdbc:mysql://localhost:3306/nike";
            String USERNAME = "root";
            String PASSWORD = "Hemanth@123";

            try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
                if(connection !=null){
                    System.out.println("Successfully Connected to DataBase for Signup");

                    String SqlCreateQuery = "Create table if not exists userAccount(userName varchar(20),userEmailid varchar(50) primary key,userPassword varchar(50))";
                    try(PreparedStatement createTableStatement = connection.prepareStatement(SqlCreateQuery)){
                        createTableStatement.executeUpdate();

                        String sqlInsertQuery = "insert into userAccount (userName,userEmailid,userPassword) values(?,?,?)";
                        try(PreparedStatement insertTableStatement = connection.prepareStatement(sqlInsertQuery)){
                            insertTableStatement.setString(1,userName);
                            insertTableStatement.setString(2,userEmail);
                            insertTableStatement.setString(3,userPassword);
                            insertTableStatement.executeUpdate();
                            System.out.println("UserCreated Sucessfully");
                        }
                    }
                }
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } catch (SQLException e) {
                request.setAttribute("status",true);
                request.getRequestDispatcher("login.jsp").forward(request,response);
                throw new RuntimeException(e);
            }


        }
        else{
            request.setAttribute("status",true);
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }
    }
}