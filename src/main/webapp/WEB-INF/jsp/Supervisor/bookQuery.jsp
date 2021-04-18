<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">    <meta charset="UTF-8">
    <title>Query a Book</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        var delayman;
        $(document).ready(function() {
            var attempt = false;
            var x = document.getElementById("messageChanging");
            x.style.display = "none";
            function queryMessage(){
                var obj;
                if(attempt){
                    obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +
                        '", "message" : "' + document.getElementById("output").value + '"}';
                } else{
                    obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +'"}';
                }
                var string = JSON.parse(obj);
                console.log("Attempting Query With " + string);
                $.ajax({
                    type: "POST",
                    url: '/Course-Message',
                    data: string,
                    dataType: 'json',
                    timeout: 6000000,
                    success: function (data) {
                        if (data.course !== document.getElementById("course").value){
                            data.message = data.course;
                        }else if(!attempt){
                            attempt = true;
                            x.style.display = "block";
                        }
                        document.getElementById('output').innerHTML = data.message;
                        console.log("SUCCESS");
                    },
                    error: function (data) {
                        console.log("FAILURE");
                    }
                })
            }
            $('#course').keydown(function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();
                    return false;
                }
            });
            $('#course').keyup(function(){
                x.style.display = "none";
                attempt = false;
                document.getElementById('output').innerHTML ="";
                clearTimeout(delayman);
                if(document.getElementById('course').value.length===9){
                    queryMessage();
                } else{
                    delayman =setTimeout(() => {
                        console.log("Checking Anyway!");
                        queryMessage();
                    }, 3000);
                }
            });
        });
    </script>
    </head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Query A Book</h2>
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
<div class="dropdown">
<a href="/" class = "dropdown-content">Log out</a>
</div>
<div class="border rounded form-group" style="width: 75%; margin-left: 20px; margin: 15px;">
<form method="post">
    <fieldset>
        <p>
            <label>Book Code:</label>
            <input id="bookCode"
                    type="text"
                   name="bookCode"
                   class="form-control"/>
            <label>Book Year:</label>
            <input id="editionYear"
                    type="text"
                   name="editionYear"
                   class="form-control"/>
            <label>Strike Bar Code:</label>
            <input id="barcode"
                    type="text"
                   name="barcode"
                   class="form-control"/>
        </p>

        <p>
            <input type="submit" name="Save" class="btn btn-primary btnCol column"/>
            <button type="button" class="btn btn-primary btnCol column" style="margin: 5px">Clear</button>
        </p>
    </fieldset>
</form>
</div>
<div class="pattinBetween border rounded" style="margin-left: 15px;">
    <div class="row">
        <div class="column left form-group">
            <form>
                <fieldset>
                    <legend>Book Info</legend>
                    <p>
                        <label>Title:</label>
                        <!--
                        <input type="text"
                               id="bookTitle"/>
                        -->
                        ${bookTitle}
                    </p>
                    <p>

                        <label>Seq Nr:</label>
                        <!--
                        <input type="text"
                               id="seqNr"/>
                        -->
                        ${seqNr}
                    </p>
                </fieldset>
                <p>
                    ${returnVoidError}
                </p>
            </form>
            <form>
                <fieldset>
                    <legend>Current Info</legend>
                    <p>
                        <label>Current Disposition:</label>
                        <!--
                        <input type="text"
                               id="bookDisposition"/>
                        -->
                        ${bookDisposition}
                    </p>
                    <p>
                        <label>Term Checked Out:</label>
                        ${termCheckedOut}
                    </p>
                    <p>
                        <label>Checked Out To:</label>
                        ${checkedOutTo}
                    </p>
                    <p>
                        <label>Date Checked Out:</label>
                        ${dateCheckedOut}
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
                        ${prevTerm}
                    </p>
                    <p>
                        <label>Previously Checked Out To:</label>
                        ${prevCheckedOutTo}
                    </p>
                    <p>
                        <label>Date Checked In:</label>
                        ${dateCheckedIn}
                    </p>
                </fieldset>
            </form>
        </div>
        <div class="column left marginftn">
            <p>
                <button type="button" class="btn btn-primary btnCol column" style="margin: 5px;">Save</button>
            </p>
            <p>
                <button type="button" class="btn btn-primary btnCol column" style="margin-top: -4.75px;">Clear</button>
            </p>
        </div>
    </div>
</div>
</body>
</html>