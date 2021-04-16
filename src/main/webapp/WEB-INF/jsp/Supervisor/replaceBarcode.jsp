<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">    <meta charset="UTF-8">
    <title>Replace Barcode Form</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        var delayman;
        $(document).ready(function() {
            var attempt = false;
            var x = document.getElementById("messageChanging");
            x.style.display = "none";
            function queryMessage(){
                var string = $("#bookCode").serialize();
                console.log("Attempting Query With " + string);
                $.ajax({
                    type: "POST",
                    url: '/Change-Barcode',
                    data: string,
                    dataType: 'json',
                    timeout: 6000000,
                    success: function (data) {
                        if (data.bookCode !== document.getElementById("bookCode").value){
                            console.log("there were errors, do the popups");
                            //data.message = data.bookCode;
                        }else if(!attempt){
                            if(document.getElementById("editionYear").value === ""){
                                document.getElementById("editionYear").placeholder = data.editionYear;
                            }
                            attempt = true;
                            x.style.display = "block";
                        }
                        console.log("SUCCESS");
                    },
                    error: function (data) {
                        console.log("FAILURE");
                    }
                })
            }
            $('#bookCode').keydown(function (e) {
                if (e.keyCode == 13) {
                    e.preventDefault();
                    return false;
                }
            });
            $('#bookCode').keyup(function(){
                x.style.display = "none";
                attempt = false;
                //document.getElementById('output').innerHTML ="";
                clearTimeout(delayman);
                if(document.getElementById('bookCode').value.length===9){
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
<body class="body">
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Replace Barcode</h2>
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
    <div class="marginftn">
<form:form method="post" action="Change-Barcode"  modelAttribute="inputNwtxdt" style="width: 75%;">
    <div class="border rounded form-group" style="width: auto; margin-left: 20px; margin: 15px;">
    <fieldset>
            <span class="col-xs-4">
            <label>Book Code</label>
            <input id="bookCode"
                    type="text"
                   name="bookCode"
                   class="form-control"/></span>
        <span class="col-xs-4">
            <label>Book Year</label>
            <input id="editionYear"
                    type="text"
                   name="editionYear"
                   class="form-control"/></span>
        <span class="col-xs-4">
            <label>Strike Barcode</label>
            <input type="text"
                   name="barcode"
                   class="form-control"/></span>

        </p>
    </fieldset>
<div class="tenPix"></div>
<fieldset id="messageChanging">
        <legend class="legend">Book Info</legend>
        <p>
            <label>Title</label>
            <input type="text"
                   name="bookTitle"
                   class="form-control left"/>
        </p>
        <p>
            <label>Seq Nr</label>
            <input type="number"
                   name="seqNumber"
                   class="form-control left"/>
        </p>
        <p>
            <label>Change Barcode To</label>
            <input type="text"
                   name="newBarcode"
                   class="form-control left"/>
        </p>
    </fieldset>
    </div>


    <span class="addlmargin">
        <input type="submit" class="btn btn-primary btnCol" name="Save">
        <button type="submit" class="btn btn-primary btnCol" name="clear">Clear</button>
    </span>

</form:form></div>
</body>
</html>