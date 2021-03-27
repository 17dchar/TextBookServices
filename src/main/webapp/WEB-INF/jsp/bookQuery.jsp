<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
    <title>Query a Book</title>
    </head>
<body>
<h1>Textbook Services: Query A Book</h1>
<div class="dropdown">
    <button class="dropbtn">Inventory</button>
    <div class="dropdown-content">
        <a href="maintenanceFormView">Maintenance</a>
        <a href="addBook">Add Books</a>
        <a href="bookQuery">Query Books</a>
        <a href="bookDisposition">Change Book Disposition</a>
        <a href="replaceBarcode">Replace Barcode</a>
        <a href="queryCourse">Query Course</a>
        <a href="courseMessage">Course Message</a>
        <a href="changeBookCode">Change Book Code/Year</a>
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
                   name="bookCode"/>
            <label>Book Year:</label>
            <input type="text"
                   name="editionYear"/>
            <label>Strike Bar Code:</label>
            <input type="text"
                   name="seqNm"/>
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
                        <input type="text"
                               id="bookTitle"/>
                    </p>
                    <p>
                        <label>Seq Nr:</label>
                        <input type="text"
                               id="seqNr"/>
                    </p>
                </fieldset>
            </form>
            <form>
                <fieldset>
                    <legend>Current Info</legend>
                    <p>
                        <label>Current Disposition:</label>
                        <input type="text"
                               id="bookDisposition"/>
                    </p>
                    <p>
                        <label>Term Check Out:</label>
                        <input type="text"
                               id="termCheckOut"/>
                    </p>
                    <p>
                        <label>Checked Out To:</label>
                        <input type="text"
                               id="checkedOutTo"/>
                    </p>
                    <p>
                        <label>Date Checked Out:</label>
                        <input type="date"
                               id="dateCheckedOut"/>
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
                        <input type="text"
                               id="previouslyTermCheckOut"/>
                    </p>
                    <p>
                        <label>Previously Checked Out To:</label>
                        <input type="text"
                               id="previouslyCheckedOutTo"/>
                    </p>
                    <p>
                        <label>Date Checked In:</label>
                        <input type="date"
                               id="dateCheckedIn"/>
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