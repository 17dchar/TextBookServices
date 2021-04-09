<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="refresh" content="3">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Check In/Out</title>
</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Check In/Out</h2>
<div class="dropdown">
    <button class="dropbtn">Patron</button>
    <div class="dropdown-content">
        <a href="Check-In-Out">Check Books In/Out</a>
        <a href="Student-Schedule">Schedule</a>
        <a href="Sold-Books">Sold Books</a>
        <a href="Previous-Books">Previous Books</a>
    </div>
</div>
<div class="tenPix"> </div>
<div class="container">
    <form class="yearCodeForm">
        <fieldset>
            <p class="">
            <div class="form-group form-inline border rounded">
                <label>Term:</label>
                <input type = "text"
                       id = "termSeason"
                       class="form-control middle pattinBetween"/>
                <input type = "text"
                       id = "termYear"
                       class="form-control middle"/>
            </div>
            <span class="betweenForm">
                <p></p>
            </span>
            <div class="form-group form-inline border rounded">
                <label style="padding-right: 20px">ID:</label>
                <input type = "text"
                       id = "bookID1"
                       class="form-control middle pattinBetween"/>
                <input type = "text"
                       id = "bookID2"
                       class="form-control"/>
            </div>
            <div class="form-group form-inline border rounded">
                <label>Bag#:</label>
                <input type = "text"
                       id = "bagNumber"
                       class="form-control middle pattinBetween"/>
            </div>
            </p>
        </fieldset>
    </form>

    <button type="button" class="btn btn-primary btnCol">New ID</button>
    <button type="button" class="btn btn-primary btnCol">Clear</button>

</div>

</body>
</html>
