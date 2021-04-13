<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <meta charset="UTF-8">
    <title>Maintenance Form</title>
</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Maintenance</h2>
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
<form method = post>
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input type="text"
                   name="bookCode"/>
            <label>Book Year:</label>
            <input type="text"
                   name="editionYear"/>
        </p>
    </fieldset>
<div class="container">
    <div class="row">
        <div class="column left">
            <form>
                <fieldset>
                    <legend>Book Info</legend>
                    <p>
                        <label>Title:</label>
                        <input type="text"
                               name="title"/>
                    </p>
                    <p>
                        <label>Author:</label>
                        <input type="text"
                               name="author"/>
                    </p>
                    <p>
                        <label>Publisher:</label>
                        <input type="text"
                               name="publisher"/>
                    </p>
                    <p>
                        <label>ISBN #:</label>
                        <input type="text"
                               name="isbn"/>
                        <label>Cost:</label>
                        <input type="text"
                               name="currentPrice"/>
                    </p>
                    <p>
                        <label>Status:</label>
                        <input type="text"
                               name="bookStatus"/>
                        <label>Purchase Date:</label>
                        <input type="date"
                               name="purchaseDate"/>
                    </p>
                    <p>
                        <label>Discontinued:</label>
                        <input type="text"
                               name="discontinuedDate"/>
                        <label>First Used Date:</label>
                        <input type="date"
                               id="firstUsedDate"/>
                    </p>
                    <p>
                        <label>Date Comment:</label>
                        <input type="text"
                               name="comment"/>
                    </p>
                </fieldset>
                <fieldset>
                    <legend>Course Info</legend>
                    <p>
                        <label>Course Title:</label>
                        <input type="text"
                               id="crseName"/>
                    </p>
                    <p>
                        <label>Course 1:</label>
                        <input type="text"
                               id="crse1"/>
                        <label>Course 4:</label>
                        <input type="text"
                               id="crse4"/>
                    </p>
                    <p>
                        <label>Course 2:</label>
                        <input type="text"
                               id="crse2"/>
                        <label>Course 5:</label>
                        <input type="text"
                               id="ccrse5"/>
                    </p>
                    <p>
                        <label>Course 3:</label>
                        <input type="text"
                               id="crse3"/>
                    </p>
                </fieldset>
        </div>
        <div class="column middle">
                <fieldset>
                    <legend>Book Stats</legend>
                    <p>
                        <label>Books Purchased:</label>
                        <input type="number"
                               id="booksPurchased"
                               value=0/>
                    </p>
                    <p>
                        <label>Books Sold:</label>
                        <input type="number"
                               id="booksSold"
                               value=0/>
                    </p>
                    <p>
                        <label>Books Unrepairable:</label>
                        <input type="number"
                               id="booksUnrepairable"
                               value=0/>
                    </p>
                    <p>
                        <label>Books Nonreturned:</label>
                        <input type="number"
                               id="booksNonreturned"
                               value=0/>
                    </p>
                    <p>
                        <label>Books In Inventory:</label>
                        <input type="number"
                               id="booksInInventory"
                               value=0/>
                    </p>
                    <p>
                        <label>Books Checked Out:</label>
                        <input type="number"
                               id="booksCheckedOut"
                               value=0/>
                    </p>
                    <p>
                        <label>Last Seq. No.:</label>
                        <input type="number" u0
                               id="lastSeqNo"
                               value=0/>
                    </p>
                </fieldset>

        </div>
        <div class="column right">
            <p>
                <input type="submit">Save</input>
            </p>
            <p>
                <button type="button">Clear</button>
            </p>
        </div>

    </div>
</div>
</form>
</body>
</html>