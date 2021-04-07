<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <meta charset="UTF-8">
    <title>Add a Book</title>
</head>

<body>
<h1>Textbook Services: Add Book</h1>
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
