<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add a Book</title>
    <style>
        .column {float: left;}
        .left {width: 60%;}
        .middle {width: 30%;}
        .right {width: 10%;}
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
        .dropbtn {
            background-color: lightgrey;
            color: darkgreen;
            padding: 10px;
            font-size: 16px;
            border: darkgreen;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 200px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .dropdown-content a {
            color: darkgreen;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {background-color: #ddd;}
        .dropdown:hover .dropdown-content {display: block;}
        .dropdown:hover .dropbtn {background-color: grey;}
    </style>
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
<form method = "post">
    <fieldset>
        Book Code: <input type="text" name="bookCode">
        Book Year: <input type="text" name="bookYear">
        <!--
        <p>
            <label>Book Code:</label>
            <input type = "text"
                   name = "bookCode" />
            <label>Book Year:</label>
            <input type = "text"
                   name = "bookYear" />
        </p>
        -->
        Title: <input type = "text" name = "bookTitle">
        Seq Nr: <input type = "text" name = "seqNm">
        Strike Bar Code: <input type = "text" name = "strikeBarCode">
        <!--
    </fieldset>
    <fieldset>
        <p>
            <label>Title:</label>
            <input type = "text"
                   name = "bookTitle" />
        </p>
        <p>
            <label>Seq Nr:</label>
            <input type = "text"
                   name = "seqNr" />
        </p>
        -->
        <!--
        <p>
            <label>Strike Bar Code:</label>
            <input type = "text"
                   id = "barCode" />
        </p>
        -->

    </fieldset>
    <div class="column right">
        <p>
            <input type="submit" name="submit">Save</input>
            <button type="button">Clear</button>
        </p>
    </div>


</form>

Here is a list of your books:
${books.get(0).getBookTitle()}
<BR/>

</body>
