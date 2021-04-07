<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/style.css">
    <meta charset="UTF-8">
    <title>Replace Barcode Form</title>
</head>
<body>
<h1>Textbook Services: Replace Barcode</h1>
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
        <p>
            <label>Book Code:</label>
            <input type="text"
                   name="bookCode"/>
            <label>Book Year:</label>
            <input type="text"
                   name="bookYear"/>
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