<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/style.css">
    <meta charset="UTF-8">
    <title>Add a Book</title>
</head>

<body>
<h1>Textbook Services: Add Book</h1>
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
<form method="post">
    <fieldset>
        <fieldset>
            <p>
                <label>Book Code: </label>
                <input type="text" name="bookCode">
                <label>Year: </label>
                <input type="text" name="editionYear">
            </p>
        </fieldset>
        <fieldset>
            <p>
                <label>Title: </label>
                <input type="text" name="bookTitle">
                <label>Sequence Number:</label>
                <input type="text" name="seqNr">
            </p>
            <p>
                <label>Strike Bar Code:</label>
                <input type="text"
                       name="barcode"/>
            </p>
        </fieldset>
    </fieldset>
    <p>
        ${returnVoidError}
    </p>
    <div class="column right">
        <p>
            <input type="submit" name="Save"/>
            <button type="button">Clear</button>
        </p>
    </div>
</form>


<!--
Here is a list of your books:
${books.get(0).getBookTitle()}
<BR/>
-->

</body>
