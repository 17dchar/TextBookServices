<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>replaceBarcodeForm.html</title>
    <style>
        .column {float: left;}
        .left {width: 90%;}
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
<h1>Textbook Services: Course Message</h1>
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
<div class="container">
    <div class="row">
        <div class="column left">
            <form>
                <fieldset>
                    <p>
                        <label>Course:</label>
                        <input type = "text"
                               id = "Course" />
                    </p>
                </fieldset>
            </form>
            <form>
                <fieldset>
                    <p>
                        <label>Message:</label>
                        <input type = "text"
                               id = "Message" />
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