<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en" style = "height: 100%">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 class="loginh1" style="padding-top:7vh;">Welcome to Textbook Services</h1>
<div class="marginftn" style="margin: 0 auto;">
<form method="post" class="login-form">
    <span class="marginAuto width85">
        <div class="login-cluster">
            <label class="loginlabel">Username:</label>
            <input class="logininput" autocomplete="off" type="text" name="login"/>
        </div>
        <div class="login-cluster">
            <label class="loginlabel">Password:</label>
            <input class="logininput" type="password"  name="password"/>
        </div>
        <div class="login-cluster">
            <input class="btn btn-primary btnCol" type="submit" value="Log In" id="loginBtn">
        </div>
    </span>
</form>
</div>
</body>
</html>