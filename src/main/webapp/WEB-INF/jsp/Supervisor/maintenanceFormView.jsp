<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/style.css">
    <meta charset="UTF-8">
    <title>Maintenance Form</title>
</head>
<body>
<h1>Textbook Services: Maintenance</h1>
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
<form>
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input type="text"
                   id="bookCode"/>
            <label>Book Year:</label>
            <input type="text"
                   id="bookYear"/>
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
                        <input type="text"
                               id="bookTitle"/>
                    </p>
                    <p>
                        <label>Author:</label>
                        <input type="text"
                               id="bookAuthor"/>
                    </p>
                    <p>
                        <label>Publisher:</label>
                        <input type="text"
                               id="bookPublisher"/>
                    </p>
                    <p>
                        <label>ISBN #:</label>
                        <input type="text"
                               id="bookISBN"/>
                        <label>Cost:</label>
                        <input type="text"
                               id="bookCost"/>
                    </p>
                    <p>
                        <label>Status:</label>
                        <input type="text"
                               id="bookStatus"/>
                        <label>Purchase Date:</label>
                        <input type="date"
                               id="bookPurchaseDate"/>
                    </p>
                    <p>
                        <label>Discontinued:</label>
                        <input type="text"
                               id="bookDiscontinued"/>
                        <label>First Used Date:</label>
                        <input type="date"
                               id="bookFirstUsed"/>
                    </p>
                    <p>
                        <label>Date Comment:</label>
                        <input type="text"
                               id="bookDateComment"/>
                    </p>
                </fieldset>
            </form>
            <form>
                <fieldset>
                    <legend>Course Info</legend>
                    <p>
                        <label>Course Title:</label>
                        <input type="text"
                               id="courseTitle"/>
                    </p>
                    <p>
                        <label>Course 1:</label>
                        <input type="text"
                               id="courseOne"/>
                        <label>Course 4:</label>
                        <input type="text"
                               id="courseFour"/>
                    </p>
                    <p>
                        <label>Course 2:</label>
                        <input type="text"
                               id="courseTwo"/>
                        <label>Course 5:</label>
                        <input type="text"
                               id="courseFive"/>
                    </p>
                    <p>
                        <label>Course 3:</label>
                        <input type="text"
                               id="courseThree"/>
                    </p>
                </fieldset>
            </form>
        </div>
        <div class="column middle">
            <form>
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