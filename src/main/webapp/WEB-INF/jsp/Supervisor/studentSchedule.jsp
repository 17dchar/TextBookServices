<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Schedule</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Schedule</h2>
<div class="dropdown">
    <button class="dropbtn">Inventory</button>
    <div class="dropdown-content">
        <a href="Maintenance-Form">Maintenance</a>
        <a href="Add-Book">Add Books</a>
        <a href="Find-Book">Query Books</a>
        <a href="Change-Disposition">Change Book Disposition</a>
        <a href="Change-Barcode">Replace Barcode</a>
        <a href="Find-Course">Query Course</a>
        <a href="Course-Message">Course Message</a>
        <a href="Change-Book-Code">Change Book Code/Year</a>
    </div>
</div>
<div class="dropdown">
    <button class="dropbtn">Patron</button>
    <div class="dropdown-content">
        <a href="Check-In-Out">Check Books In/Out</a>
        <a href="Student-Schedule">Schedule</a>
        <a href="Sold-Books">Sold Books</a>
        <a href="Previous-Books">Previous Books</a>
    </div>
</div>
<a href="/" class = "dropbtn">Log out</a>
<div class="tenPix"></div>
<div class="container">
    <div class="container">
        <form class="yearCodeForm" method="post">
            <fieldset>
                <p>
                <div class="form-group">
                <label>Term:</label>
                    <input type = "text"
                           name = "termSeason"
                           class="form-control fiftyWidth"/>
                    </div></p>
                </fieldset>
                <fieldset>
                    <p>
                <div class="form-group">
                <label>ID:</label>
                    <input type = "text"
                           name = "id"
                           class="form-control fiftyWidth"/>
                </div>
                    </p>
                    <input type="submit" name="Save"/>
            </fieldset>
        </form>

        <c:forEach items="${output}" var="output" varStatus="status">
                <p>Title: ${outputTitle[status.index]}</p>
                <p>Meta Data: ${output.subjCode}, ${output.crseNumb}, ${output.seqNumb}, ${output.ptrmCode}</p>
                <p>Times: ${outputTimes[status.index].monday} ${outputTimes[status.index].tuesday} ${outputTimes[status.index].wednesday}
                        ${outputTimes[status.index].thursday} ${outputTimes[status.index].friday}</p>
        </c:forEach>

    </div>

</div>

</body>
</html>