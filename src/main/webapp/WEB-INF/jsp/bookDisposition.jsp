<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
    <title>Book Disposition</title>
</head>
<body>
<h1>Textbook Services: Change Book Disposition</h1>
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
<form method = "post">
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input type="text"
                   id="bookCode"/>
            <label>Book Year:</label>
            <input type="text"
                   id="bookYear"/>
            <label>Strike Bar Code:</label>
            <input type="text"
                   id="barCode"/>
        </p>
    </fieldset>
</form>
<div class="container">
    <div class="row">
        <div class="column left">
            <form method = "post">
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
                    <p>
                        <label>Current Disposition:</label>
                        <input type="text"
                               id="bookDisposition"/>
                    </p>
                    <p>
                        <label for="bookDisposition">Change Disposition To:</label>
                        <select name="bookDisposition" id="bookDisposition">
                            <option value="bookDisposition">(No Change)</option>
                            <option value="bookDisposition">I</option>
                            <option value="bookDisposition">O</option>
                            <option value="bookDisposition">S</option>
                            <option value="bookDisposition">N</option>
                        </select>
                    </p>
                </fieldset>
        </div>
        <div class="column right">
            <p>
                <input type="submit" name="Save"/>
            </p>
            <p>
                <button type="button">Clear</button>
            </p>
        </div>

        </form>
    </div>
</div>
</body>
</html>