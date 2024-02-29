package com.example.urbanship;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "logout", value = "/logout")
public class logout extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("Emailid");
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}