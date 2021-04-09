<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sold Books</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Sold Books</h2>
<div class="dropdown">
    <button class="dropbtn">Patron</button>
    <div class="dropdown-content">
        <a href="Check-In-Out">Check Books In/Out</a>
        <a href="Student-Schedule">Schedule</a>
        <a href="Sold-Books">Sold Books</a>
        <a href="Previous-Books">Previous Books</a>
    </div>
</div>
<div class="tenPix"></div>
<div class="container">
    <div class="container">
        <form class="yearCodeForm" method = "post">
            <fieldset>
                <p>
                    <label>919#</label>
                    <input type = "text"
                           name = "id"/>
                    <input type="submit" name="Find Them!"/>
                    <label>Term:</label>
                    <input type = "text"
                           id = "termSeason" />
                    <input type = "text"
                           id = "termYear" />
                    <label>ID:</label>
                    <input type = "text"
                           id = "bookID1" />
                    <input type = "text"
                           id = "bookID2" />
                    <label>Bag#:</label>
                    <input type = "text"
                           id = "bagNumber" />
                </p>
            </fieldset>
        </form>
    </div>

    <c:forEach items="${soldBooks}" var="soldBooks">
        <p>
            <p>${soldBooks.bookCode}</p>
        </p>
    </c:forEach>

</div>

</body>
</html>