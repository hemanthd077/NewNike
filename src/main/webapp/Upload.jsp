<%--
  Created by IntelliJ IDEA.
  User: heman-pt-7356
  Date: 03/01/24
  Time: 5:15 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UploadShoes</title>
    <link rel="icon" href="Image/nikeblack.png" type="image/x-icon">
    <link rel="stylesheet" href="CSS/upload.css">
    <link rel="stylesheet" href="CSS/collection.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //http 1.1
        response.setHeader("pragma","no-cache");    //http 1.0

        response.setHeader("Expires","0"); //proxies

        if(session.getAttribute("userEmailid")==null){
            response.sendRedirect("login.jsp");
        }
    %>
    <div class="columnFlex OuterBlock">
        <div class="rowFlex menuBlock">
            <img src="/Image/NikeLogo.png" class="AppIcon">
            <div class="rowFlex mainButton">
                <form method="get" action="home" ><input class="NonActiveState" value="HOME" type="submit"></form>
                <form method="get" action="collection" ><input type="submit" value="COLLECTION" class="NonActiveState"></form>
                <form method="get" action="contact" ><input value="CONTACT" type="submit" class="NonActiveState"></form>
                <form method="get" action="about" href="about.jsp" ><input value="ABOUT" class="NonActiveState" type="submit"></form>
                <a href="Upload.jsp" class="activeState">UPLOAD</a>
            </div>
            <div class="rowFlex rightMenuContent">
                            <span class="rowFlex MainSearchBox">
                                <svg style="margin-top:5px;margin-left: 10px;" xmlns="http://www.w3.org/2000/svg" fill="#ffffff" height="20" width="20" viewBox="0 0 512 512"><path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"/></svg>
                                <input type="text" placeholder="Search" class="searchBox"/>
                            </span>
                <form action="cart" method="get">
                    <button type="submit" class="CartButton">
                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" fill="#ffffff" viewBox="0 0 576 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z"/></svg>
                    </button>
                </form>
                <form action="logout" method="get">
                    <button type="submit" class="CartButton">
                        <svg xmlns="http://www.w3.org/2000/svg" height="20" width="20" fill="#ffffff" viewBox="0 0 512 512"><path d="M502.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-128-128c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L402.7 224 192 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l210.7 0-73.4 73.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l128-128zM160 96c17.7 0 32-14.3 32-32s-14.3-32-32-32L96 32C43 32 0 75 0 128L0 384c0 53 43 96 96 96l64 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32l0-256c0-17.7 14.3-32 32-32l64 0z"/></svg>
                    </button>
                </form>
            </div>
        </div>
        <div class="columnFlex MainOuterDiv">
            <form method="post" action="UploadShoe" enctype="multipart/form-data" class="columnFlex mainform">
                <h1>UPLOAD SHOES</h1><br>
                <input type="text" name="productName" class="uploadInput" placeholder="Product Name">
                <input type="number" name="productPrice" class="uploadInput" placeholder="Enter the Price in Rupee">
                <textarea rows="5" cols="50" name="productDescription" class="uploadDescription" placeholder="Enter the Product Description.."></textarea>
                <div id="upload-container" class="rowFlex">
                    <label for="file-input" id="upload-label">Choose or Drag & Drop an Image</label>
                    <input type="file" id="file-input" name="upfile" accept="image/*">
                    <div id="preview-image"></div>
                </div>
                <input type="submit" class="uploadSubmit" value="Upload">
            </form>
        </div>
    </div>
</body>
</html>
