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
<body>
<h1 class="loginh1">Log In:</h1>
<form class="loginform" method="post">
    <label class="loginlabel">Username:</label>
    <input class="logininput" type="text" name="login"/>
    <label class="loginlabel">Password:</label>
    <input class="logininput" type="text" name="password"/>
    <input class="logininput" type="submit" value="Submit" id="loginBtn">
</form>
</body>
</html>