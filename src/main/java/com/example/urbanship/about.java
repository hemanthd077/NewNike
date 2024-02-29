package com.example.urbanship;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "about", value = "/about")
public class about extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("about.jsp");
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//    }
}