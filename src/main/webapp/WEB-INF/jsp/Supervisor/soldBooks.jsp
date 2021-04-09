<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sold Books</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <style>
        .TBSHeader {
            width: 100%;
            height: 30vh;
            background-size: cover;
            background-image: url('https://www.nwmissouri.edu/images/header-img/drone campus august fall2018 tw 02069.jpg');
            background-position-y:center;
            text-align: center;
            color: #f1f1f1;
            -webkit-text-stroke-width: .5pt;
            -webkit-text-stroke-color: #000000;
            font-family: "Oswald", Arial, sans-serif;
            text-transform: uppercase;
            font-size: 56px;
            font-weight: bolder;
            line-height: 2em;
            border-bottom: #006747;
            border-bottom-style: inset;
            border-bottom-width: 45px;
            margin-bottom: -126.5px;
            text-shadow: #006747;
        }
        .BookCodeYearTitle {
            text-align: center;
            height: 102px;
            font-weight: bolder;
            text-shadow: #000000;
            font-family: "Oswald", Arial, sans-serif;
            color: #f1f1f1;
            font-size: 36px;
            -webkit-text-stroke-width: .5pt;
            -webkit-text-stroke-color: #000000;
            line-height: 1em;
            padding-bottom: 10px;
            margin-bottom: -20px;
        }
        .btnCol {
            background-color: #006747;
            outline-color: black;
        }
        .btnCol:hover { background-color: #014811}

        .form-control:focus {
            border-color: #006747;
            box-shadow: 0 0 0 1.5px rgb(0, 103, 71);
        }
        .has-error .form-control:focus{
            box-shadow: none;
            -webkit-box-shadow: none;}

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
            background-color: #006747;
            color: #f1f1f1;
            padding: 10px;
            font-size: 16px;
            border: #f1f1f1;
        }
        .dropdown {
            position: relative;
            display: inline-block;
            margin-left: 20px;
            margin-bottom: 6px;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #006747;
            min-width: 200px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
            margin-left: 2px;
        }
        .dropdown-content a {
            color: #f1f1f1;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {background-color: #014811;}
        .dropdown:hover .dropdown-content {display: block;}
        .dropdown:hover .dropbtn {background-color: #006747;}
        .container {
            margin-top: 15px;
            margin-left: 20px;
            float: left;
        }
        .tenPix {
            height: 10px;
            width: 100%;
        }
    </style>
</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Sold Books</h2>
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
<a href="/" class = "dropbtn">Log out</a>
<div class="tenPix"></div>
<div class="container">
    <div class="container">
        <form class="yearCodeForm" method = "post">
            <fieldset>
                <p>
                    <label>919#</label>
                    <input type = "text"
                           name = "id"/>
                    <input type="submit" name="Find Them!"/>
                    <label>Term:</label>
                    <input type = "text"
                           id = "termSeason" />
                    <input type = "text"
                           id = "termYear" />
                    <label>ID:</label>
                    <input type = "text"
                           id = "bookID1" />
                    <input type = "text"
                           id = "bookID2" />
                    <label>Bag#:</label>
                    <input type = "text"
                           id = "bagNumber" />
                </p>
            </fieldset>
        </form>
    </div>

    <c:forEach items="${soldBooks}" var="soldBooks">
        <p>
            <p>${soldBooks.bookCode}</p>
        </p>
    </c:forEach>

</div>

</body>
</html>