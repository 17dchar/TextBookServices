<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Change Book Code/Year</title>
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
                var changingBookCode = false;
                var changingEditionYear = false;

                //AJAX Function
                function queryMessage(){
                    var obj;
                    if(withEditionYear){
                        if(changingBookCode  && changingEditionYear){
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "currentEditionYear" : "' + document.getElementById("editionYear").value +
                                '", "changingBookCode" : "' + document.getElementById("newBookCode").value +
                                '", "changingEditionYear" : "' + document.getElementById("newEditionYear").value + '"}';
                        } else if(changingBookCode && !changingEditionYear){
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "currentEditionYear" : "' + document.getElementById("editionYear").value +
                                '", "changingBookCode" : "' + document.getElementById("newBookCode").value + '"}';
                        } else if(!changingBookCode && changingEditionYear){
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "currentEditionYear" : "' + document.getElementById("editionYear").value +
                                '", "changingEditionYear" : "' + document.getElementById("newEditionYear").value + '"}';
                        } else{
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "currentEditionYear" : "' + document.getElementById("editionYear").value + '"}';
                        }
                    }else{
                        if(changingBookCode  && changingEditionYear){
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "changingBookCode" : "' + document.getElementById("newBookCode").value +
                                '", "changingEditionYear" : "' + document.getElementById("newEditionYear").value + '"}';
                        } else if(changingBookCode && !changingEditionYear){
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "changingBookCode" : "' + document.getElementById("newBookCode").value + '"}';
                        } else if(!changingBookCode && changingEditionYear){
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value +
                                '", "changingEditionYear" : "' + document.getElementById("newEditionYear").value + '"}';
                        } else{
                            obj = '{ "currentBookCode" : "' + document.getElementById("bookCode").value + '"}';
                        }
                    }
                    var string = JSON.parse(obj);
                    console.log("Query With " + string);

                    //AJAX
                    $.ajax({
                        type: "POST",
                        url: '/Change-Book-Code',
                        data: string,
                        dataType: 'json',
                        timeout: 6000000,
                        success: function (data) {
                            if(data.errors){
                                alert(data.errorMessage);
                                return;
                            }else if(!withEditionYear) {
                                document.getElementById('editionYear').placeholder = data.editionYear;
                            }
                            console.log("SUCCESS");
                        },
                        error: function (data) {
                            console.log("FAILURE");
                        }
                    })
                }

                //Nullify bookcode enter key presses
                $('#bookCode').keydown(function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        return false;
                    }
                });
                //If there is change in the bookcode, check if it's valid to query off of
                $('#bookCode').keyup(function(){
                    withEditionYear = false;
                    changingVariables = false;
                    //document.getElementById('output').innerHTML ="";
                    clearTimeout(delayman);
                    if(document.getElementById('bookCode').value.length===8){
                        queryMessage();
                    } else{
                        //If there is no change for 3 seconds, assume that the user is waiting for
                        //The query, and query anyway (will likely get caught by data validation)
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
                    changingBookCode = false;
                    changingEditionYear = false;
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

                //Check if new New edition year is valid, then send change request
                $('#newEditionYear').keydown(function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        if(document.getElementById('newEditionYear').value.length!==0){
                            changingEditionYear = true;
                        }
                        if(document.getElementById('newBookCode').value.length!==0){
                            changingBookCode = true;
                        }
                        queryMessage();
                        return false;
                    }
                });

                //Clear all input fields
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
            });
        </script>
    </head>
    <body>
        <div class="TBSHeader">
            <h1 class="page-title" >Change Book Code / Year</h1>
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
        <div class="tenPix"></div>
        <div class="page-body" style="height: 80vh;">
            <div class="border rounded" style="width: 60%; margin-left: 15px;">
                <form:form  class="yearCodeForm" method = "post" action="Change-Barcode" modelAttribute="inputNwtxdt">
                    <fieldset>
                        <p>
                        <div class="border rounded">
                            <label>Book Code:</label>
                            <input id="bookCode"
                                    type = "text"
                                   name = "bookCode"
                                   class="form-control"/>
                            <label>Edition Year:</label>
                            <input id="editionYear"
                                    type = "text"
                                   name = "editionYear"
                                   class="form-control"/>
                        </div>
                        <div class="tenPix"></div>
                        <div class="border rounded" id="messageChanging">
                            <label>New Book Code:</label>
                            <input id="newBookCode"
                                    type = "text"
                                   name = "newBookCode"
                                   class="form-control"/>
                            <label>New Book year:</label>
                            <input id="newEditionYear"
                                    type = "text"
                                   name = "newEditionYear"
                                   class="form-control"/>
                            </p>
                        </div>
                        <!--
                        <div class="tenPix">
                        </div>
                        <input type="submit" class="btn btn-primary btnCol column" name="Save" style="width: 100%; align-self: center;"/>
                        -->
                        <p>
                            <button type="button" id="makeChanges" class="btn btn-primary btnCol">Save</button>
                        </p>
                        <p>
                            <button type="button" id="clear" class="btn btn-primary btnCol">Clear</button>
                        </p>
                    </fieldset>
                </form:form>
            </div>
        </div>
    </body>
</html>
