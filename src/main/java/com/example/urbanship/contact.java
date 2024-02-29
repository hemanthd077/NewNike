package com.example.urbanship;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "contact", value = "/contact")
public class contact extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("contact.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}