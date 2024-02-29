package com.example.urbanship;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String Emailid = request.getParameter("userEmailid");
        String Password = request.getParameter("userPassword");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String URL = "jdbc:mysql://localhost:3306/nike";
        String USERNAME = "root";
        String PASSWORD = "Hemanth@123";

        try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String SqlFindQuery = "select * from userAccount where userEmailid=? and userPassword=?";

            try(PreparedStatement preparedStatement = connection.prepareStatement(SqlFindQuery)){
                preparedStatement.setString(1,Emailid);
                preparedStatement.setString(2,Password);

                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        HttpSession session = request.getSession();
                        session.setAttribute("userEmailid",Emailid);
                        response.sendRedirect("home.jsp");
                    }
                    else{
                        request.setAttribute("Loginstatus",true);
                        request.getRequestDispatcher("login.jsp").forward(request,response);
                    }
                }
            }

        }catch (Exception e){
            System.out.println(e);
            request.setAttribute("Loginstatus",true);
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }
}