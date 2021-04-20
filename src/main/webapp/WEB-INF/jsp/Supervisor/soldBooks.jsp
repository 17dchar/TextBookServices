<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sold Books</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

        var delayman;
        $(document).ready(function() {
            var withEditionYear = false;
            var withBarcode = false;
            function queryMessage(){
                var obj;
                if(withEditionYear){
                    obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +
                        '", "editionYear" : "' + document.getElementById("editionYear").value + '"}';
                } else if(withBarcode) {
                    obj = '{ "barcode" : "' + document.getElementById("barcode").value +'"}';
                } else{
                    obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +'"}';
                }
                var string = JSON.parse(obj);
                console.log("Query With " + string);
                $.ajax({
                    type: "POST",
                    url: '/Find-Book',
                    data: string,
                    dataType: 'json',
                    timeout: 6000000,
                    success: function (data) {
                        if (data.errors){
                            console.log(data);
                        }else if(!withEditionYear) {
                            document.getElementById('editionYear').placeholder = data.editionYear;
                        }
                        if(withBarcode){
                            document.getElementById('bookCode').placeholder = data.bookCode;
                            document.getElementById('editionYear').placeholder = data.editionYear;
                        }
                        document.getElementById('title').innerHTML = data.title;
                        document.getElementById('seqNr').innerHTML = data.seqNr;
                        document.getElementById('currentDisposition').innerHTML = data.currentDisposition;
                        document.getElementById('currentTermCheckOut').innerHTML = data.currentTermCheckOut;
                        document.getElementById('currentCheckedOutTo').innerHTML = data.currentCheckedOutTo;
                        document.getElementById('currentDateCheckedOut').innerHTML = data.currentDateCheckedOut;
                        document.getElementById('previousTermCheckedOut').innerHTML = data.previousTermCheckedOut;
                        document.getElementById('previousCheckedOutTo').innerHTML = data.previousTermCheckedOut;
                        document.getElementById('previousDateCheckedIn').innerHTML = data.previousDateCheckedIn;

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
                withEditionYear = false;
                //document.getElementById('output').innerHTML ="";
                clearTimeout(delayman);
                if(document.getElementById('bookCode').value.length===8){
                    queryMessage();
                } else{
                    delayman =setTimeout(() => {
                        if(document.getElementById('bookCode').value.length!==0) {
                            console.log("Checking Anyway With Only Book Code!");
                            queryMessage();
                        }
                    }, 3000);
                }
            });
            $('#editionYear').keyup(function(){
                withEditionYear = false;
                clearTimeout(delayman);
                if(document.getElementById('editionYear').value.length===4){
                    withEditionYear = true;
                    queryMessage();
                } else{
                    delayman =setTimeout(() => {
                        if(document.getElementById('editionYear').value.length!==0) {
                            console.log("Checking Anyway With Edition Year And Book Code!");
                            withEditionYear = true;
                            queryMessage();
                        }
                    }, 3000);
                }
            });
            $('#barcode').unbind().bind('keyup',(function(){
                withBarcode = false;
                //document.getElementById('output').innerHTML ="";
                clearTimeout(delayman);
                if(document.getElementById('barcode').value.length===13 || document.getElementById('barcode').value.length===12){
                    console.log("Length Met. Will Check With Only Barcode");
                    withBarcode = true;
                    queryMessage();
                } else{
                    delayman =setTimeout(() => {
                        if(document.getElementById('barcode').value.length!==0){
                            console.log("Checking Anyway With Barcode!");
                            console.log(document.getElementById('barcode').value);
                            withBarcode = true;
                            queryMessage();
                        }
                    }, 3000);
                }
            }));
        });
    </script>
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