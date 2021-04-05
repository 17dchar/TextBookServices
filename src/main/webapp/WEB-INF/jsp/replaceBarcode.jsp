<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
    <title>Replace Barcode Form</title>
</head>
<body>
<h1>Textbook Services: Replace Barcode</h1>
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
                   name="bookYear"/>
            <label>Strike Barcode:</label>
            <input type="text"
                   name="barcode"/>
        </p>
    </fieldset>
    <button type="submit">Save</button>
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
                        <input type="number"
                               id="seqNumber"/>
                    </p>
                    <p>
                        <label>Change Barcode To:</label>
                        <input type="text"
                               id="newBarcode"/>
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