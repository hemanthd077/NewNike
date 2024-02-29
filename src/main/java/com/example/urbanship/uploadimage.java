package com.example.urbanship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(name = "UploadShoe", value = "/UploadShoe")
@MultipartConfig
public class uploadimage extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "/uploadImages";

    static String generateUniqueId(){
        Date currentDate = new Date(System.currentTimeMillis());

        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = dateFormat.format(currentDate);
        // Combine date and ID
        String finalId = formattedDate+"";
        System.out.println(finalId);
        return finalId;
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("upfile");

            // Check if the filePart is not null
            if (filePart != null) {
                String fileName = getFileName(filePart);

                // Specify the absolute path where you want to store the uploaded files
                String appPath = "/home/heman-pt-7356/IdeaProjects/Urbanship/src/main/webapp";
                Path uploadPath = Path.of(appPath, UPLOAD_DIRECTORY);
                Files.createDirectories(uploadPath);

                // Append the product name to the file name (adjust this part as needed)
                String productName = request.getParameter("productName");
                String productPrice = request.getParameter("productPrice");
                String productDescription = request.getParameter("productDescription");

                String PID = generateUniqueId();

                String uniqueFileName = PID+"-ProductImage";

                Path targetFilePath = uploadPath.resolve(uniqueFileName);
                Files.copy(filePart.getInputStream(), targetFilePath);

                System.out.println("File stored at: " + targetFilePath);
                System.out.println("File Uploaded Successfully");

                //Uploading data to database
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String URL = "jdbc:mysql://localhost:3306/nike";
                String USERNAME = "root";
                String PASSWORD="Hemanth@123";
                try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                    if (connection != null) {
                        System.out.println("Connected to the database!");

                        // Step 2: Creating a statement
                        String createTableQuery = "CREATE TABLE IF NOT EXISTS nikeProducts (productId varchar(20) PRIMARY KEY,productName varchar(50),productPrice varchar(20),productDescription varchar(200),productURL varchar(500))";
                        try (PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery)) {
                            // Execute the statement to create the table
                            createTableStatement.executeUpdate();
                            System.out.println("Table created or already exists!");

                            // Step 3: Inserting data
                            String insertDataQuery = "INSERT INTO nikeProducts (productId , productName, productPrice, productDescription,productURL) VALUES (?,?,?,?,?)";
                            try (PreparedStatement insertDataStatement = connection.prepareStatement(insertDataQuery)) {
                                // Insert data into the table
                                insertDataStatement.setString(1, PID);
                                insertDataStatement.setString(2, productName);
                                insertDataStatement.setString(3, productPrice);
                                insertDataStatement.setString(4,productDescription);
                                insertDataStatement.setString(5,"/uploadImages/"+PID+"-ProductImage");
                                insertDataStatement.executeUpdate();
                                System.out.println("Data inserted into the table!");
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                response.sendRedirect("home.jsp");
            } else {
                // Handle the case when filePart is null
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file received");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "unknown";
    }
}
