
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="icon" href="Image/nikeblack.png" type="image/x-icon">
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="OuterBlock rowFlex">
    <img src="Image/NikeLogo.png" class="AppIconMain">
    <div class="rowFlex signupmaindiv">
        <img src="Image/Finish%20line-amico.png" class="signupimage">
        <div class="loginDiv columnFlex">
            <h2>SIGNUP</h2>
            <form action="signup" method="post" class="columnFlex formloginCont">
                <input type="text" class="inputBox" placeholder="Enter the UserName" name="userName">
                <input type="email" class="inputBox" placeholder="Enter the Emailid" name="userEmailid">
                <input type="Password" class="inputBox" placeholder="Enter the Password" name="userPassword">
                <input type="text" class="inputBox" placeholder="Re-Enter the Password" name="UserConfirmPassword">
                <input type="submit" id="submitBtn" value="SIGNUP">
            </form>
            <%
                if(request.getAttribute("status")!=null){
                    if((boolean)request.getAttribute("status")){%>
            <p>Something gone wrong signup failed Try Again</p>

            <%} } %>
            <p>OR</p><br/>
            <a  class="navText" href="login.jsp">LOGIN</a>
        </div>
    </div>
</div>
</body>
</html>
















<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
    <link rel="stylesheet" href="CSS/login.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
    <div>

    </div>
</body>
</html>
