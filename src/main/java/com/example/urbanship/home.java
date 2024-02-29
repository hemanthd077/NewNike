package com.example.urbanship;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "home", value = "/home")
public class home extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("home.jsp");

    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//    }
}