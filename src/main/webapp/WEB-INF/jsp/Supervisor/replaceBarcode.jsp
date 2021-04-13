<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">    <meta charset="UTF-8">
    <title>Replace Barcode Form</title>
</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Replace Barcode</h2>
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
<div class="dropdown">
    <button class="dropbtn">Reports</button>
    <div class="dropdown-content">
        <a href="Report">Add Report Here</a>
    </div>
</div>
<a href="/" class = "dropbtn">Log out</a>
<form:form method="post">
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input type="text"
                   name="bookCode"/>
            <label>Book Year:</label>
            <input type="text"
                   name="editionYear"/>
            <label>Strike Barcode:</label>
            <input type="text"
                   name="barcode"/>
        </p>
    </fieldset>
    <button type="submit">Save</button>

<div class="container">
    <div class="row">
        <div class="column left">

                <fieldset>
                    <legend>Book Info</legend>
                    <p>
                        <label>Title:</label>
                        <input type="text"
                               name="bookTitle"/>
                    </p>
                    <p>
                        <label>Seq Nr:</label>
                        <input type="number"
                               name="seqNumber"/>
                    </p>
                    <p>
                        <label>Change Barcode To:</label>
                        <input type="text"
                               name="newBarcode"/>
                    </p>
                </fieldset>
            </form:form>
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