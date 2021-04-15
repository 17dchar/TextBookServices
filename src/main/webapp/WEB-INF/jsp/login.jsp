<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<h1 class="loginh1">Log In:</h1>
<body style="background-size: cover;
    background-image: url('https://www.nwmissouri.edu/images/header-img/drone%20campus%20august%20fall2018%20tw%2002069.jpg');
    background-position-y: center;
    background-repeat: no-repeat;
    background-size: 100%;">
<div class="marginftn">
<form method="post" class="loginform">
    <span class="marginAuto width85">
    <label class="loginlabel">Username:</label>
    <input class="logininput" type="text" name="login"/>
    <label class="loginlabel">Password:</label>
    <input class="logininput" type="text" name="password"/>
    <input class="btn btn-primary btnCol" type="submit" value="Submit" id="loginBtn" style="color: #f1f1f1">
    </span>
</form>
</div>
</body>
</html>