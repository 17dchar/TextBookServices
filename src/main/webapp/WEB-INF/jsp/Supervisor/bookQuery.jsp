<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">    <meta charset="UTF-8">
    <title>Query a Book</title>
    </head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Query A Book</h2>
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
        <a href="#">blah</a>
    </div>
</div>
<div class="dropdown">
    <button class="dropbtn">Reports</button>
    <div class="dropdown-content">
        <a href="#">blah</a>
    </div>
</div>
<form method="post">
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input type="text"
                   name="bookCode"
                    required = "required"/>
            <label>Book Year:</label>
            <input type="text"
                   name="editionYear"/>
            <label>Strike Bar Code:</label>
            <input type="text"
                   name="barcode"/>
        </p>
        <p>
            <input type="submit" name="Save"/>
            <button type="button">Clear</button>
        </p>
    </fieldset>
</form>
<div class="container">
    <div class="row">
        <div class="column left">
            <form>
                <fieldset>
                    <legend>Book Info</legend>
                    <p>
                        <label>Title:</label>
                        <!--
                        <input type="text"
                               id="bookTitle"/>
                        -->
                        ${bookTitle}
                    </p>
                    <p>

                        <label>Seq Nr:</label>
                        <!--
                        <input type="text"
                               id="seqNr"/>
                        -->
                        ${seqNr}
                    </p>
                </fieldset>
                <p>
                    ${returnVoidError}
                </p>
            </form>
            <form>
                <fieldset>
                    <legend>Current Info</legend>
                    <p>
                        <label>Current Disposition:</label>
                        <!--
                        <input type="text"
                               id="bookDisposition"/>
                        -->
                        ${bookDisposition}
                    </p>
                    <p>
                        <label>Term Checked Out:</label>
                        ${termCheckedOut}
                    </p>
                    <p>
                        <label>Checked Out To:</label>
                        ${checkedOutTo}
                    </p>
                    <p>
                        <label>Date Checked Out:</label>
                        ${dateCheckedOut}
                    </p>
                </fieldset>
            </form>
        </div>
        <div class="column middle">
            <form>
                <fieldset>
                    <legend>Previous Info</legend>
                    <p>
                        <label>Previous Term Check Out:</label>
                        ${prevTerm}
                    </p>
                    <p>
                        <label>Previously Checked Out To:</label>
                        ${prevCheckedOutTo}
                    </p>
                    <p>
                        <label>Date Checked In:</label>
                        ${dateCheckedIn}
                    </p>
                </fieldset>
            </form>
        </div>
        <div class="column right">
            <p>
                <button type="button">Save</button>
            </p>
            <p>
                <button type="button">Clear</button>
            </p>
        </div>
    </div>
</div>
</body>
</html>