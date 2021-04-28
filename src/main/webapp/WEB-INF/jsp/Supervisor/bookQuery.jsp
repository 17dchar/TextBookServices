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
                var withEditionYear = false;
                var withBarcode = false;

                //AJAX FUNCTION
                function queryMessage(){
                    var obj;
                    //If there is an edition year, put it in json
                    if(withEditionYear){
                        obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +
                            '", "editionYear" : "' + document.getElementById("editionYear").value + '"}';
                    } else if(withBarcode) {
                        //If there is a barcode, put it in json
                        obj = '{ "barcode" : "' + document.getElementById("barcode").value +'"}';
                    } else{
                        obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +'"}';
                    }
                    var string = JSON.parse(obj);
                    console.log("Query With " + string);

                    //AJAX
                    $.ajax({
                        type: "POST",
                        url: '/Find-Book',
                        data: string,
                        dataType: 'json',
                        timeout: 6000000,
                        success: function (data) {

                            //Populate page
                            if(data.errors){
                                alert(data.errorMessage);
                                return;
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

                //Nullify all enter key presses in bookcode field
                $('#bookCode').keydown(function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        return false;
                    }
                });

                //If there is change in the bookcode, check if it's valid to query off of
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

                //If there is change in the edition year, check if it's valid to query off of
                $('#editionYear').keyup(function(){
                    withEditionYear = false;
                    clearTimeout(delayman);
                    if(document.getElementById('editionYear').value.length===4){
                        withEditionYear = true;
                        queryMessage();
                    } else{
                        //If there is no change for 3 seconds, assume that the user is waiting for
                        //The query, and query anyway (will likely get caught by data validation)
                        delayman =setTimeout(() => {
                            if(document.getElementById('editionYear').value.length!==0) {
                                console.log("Checking Anyway With Edition Year And Book Code!");
                                withEditionYear = true;
                                queryMessage();
                            }
                        }, 3000);
                    }
                });

                //If there is change in the barcode, check if it's valid to query off of
                $('#barcode').unbind().bind('keyup',(function(){
                    withBarcode = false;
                    //document.getElementById('output').innerHTML ="";
                    clearTimeout(delayman);
                    if(document.getElementById('barcode').value.length===13 || document.getElementById('barcode').value.length===12){
                        console.log("Length Met. Will Check With Only Barcode");
                        withBarcode = true;
                        queryMessage();
                    } else{
                        //If there is no change for 3 seconds, assume that the user is waiting for
                        //The query, and query anyway (will likely get caught by data validation)
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

                //Clear all data in inputs
                $('#clear').click(function (){
                    console.log("click");
                    $('input[type=text]').each(function(){
                        var id = $(this).attr('id');
                        console.log(id);
                        $(this).attr('placeholder',"");
                        $(this).val("");
                    });
                    $('p[type=text]').each(function(){
                        var id = $(this).attr('id');
                        console.log(id);
                        $(this).text("");
                    });
                    $('input[type=date]').each(function(){
                        var id = $(this).attr('id');
                        console.log(id);
                        $(this).val("");
                    });
                });
                $('#makeChanges').click(function (){
                    console.log("click");
                    queryMessage();
                });
            });
        </script>
        </head>
    <body>
        <div class="TBSHeader">
            <h1 class="page-title" >Find Book</h1>
        </div>
        <h2 class="BookCodeYearTitle"></h2>
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
        <div class="page-body" style="height: 80vh;">
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
                            <button type="button" id="makeChanges" class="btn btn-primary btnCol">Find Book</button>
                            <button type="button" id="clear" class="btn btn-primary btnCol column" style="margin: 5px">Clear</button>
                        </p>
                    </fieldset>
                </form>
            </div>
            <div class="pattinBetween border rounded" style="margin-left: 15px; height: auto; display:flex; flex-direction: column; flex-grow: 1;">
                <div class="row">
                    <div class="column left form-group">
                        <fieldset>
                            <legend>Book Info</legend>
                            <p>
                                <label style = "display: inline-block;">Title:</label>
                                <p type="text" id ='title'></p>
                                <!--${bookTitle}-->
                            </p>
                            <p>

                                <label>Seq Nr:</label>
                                <p type="text" id ='seqNr'></p>
                                <!--${seqNr}-->
                            </p>
                        </fieldset>
                        <fieldset>
                            <legend>Current Info</legend>
                            <p>
                                <label>Current Disposition:</label>
                                <p type="text" id ='currentDisposition'></p>
                                <!--${bookDisposition}-->
                            </p>
                            <p>
                                <label>Term Checked Out:</label>
                                <p type="text" id ='currentTermCheckOut'></p>
                                <!--${termCheckedOut}-->
                            </p>
                            <p>
                                <label>Checked Out To:</label>
                                <p type="text" id ='currentCheckedOutTo'></p>
                                <!--${checkedOutTo}-->
                            </p>
                            <p>
                                <label>Date Checked Out:</label>
                                <p type="text" id ='currentDateCheckedOut'></p>
                                <!--${dateCheckedOut}-->
                            </p>
                        </fieldset>
                    </div>
                    <div class="column middle">
                        <fieldset>
                            <legend>Previous Info</legend>
                            <p>
                                <label>Previous Term Check Out:</label>
                                <p type="text" id ='previousTermCheckedOut'></p>
                                <!--${prevTerm}-->
                            </p>
                            <p>
                                <label>Previously Checked Out To:</label>
                                <p type="text" id ='previousCheckedOutTo'></p>
                                <!--${prevCheckedOutTo}-->
                            </p>
                            <p>
                                <label>Date Checked In:</label>
                                <p type="text" id ='previousDateCheckedIn'></p>
                                <!--${dateCheckedIn}-->
                            </p>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>