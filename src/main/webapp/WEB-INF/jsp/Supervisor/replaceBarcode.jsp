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
                            $('input[type=text]').each(function(){
                                var id = $(this).attr('id');
                                console.log(id);
                                $(this).attr('placeholder',data[id.toString()]);
                            });
                            $('p[type=text]').each(function(){
                                var id = $(this).attr('id');
                                console.log(id);
                                $(this).text(data[id.toString()]);
                            });
                            $('input[type=date]').each(function(){
                                var id = $(this).attr('id');
                                console.log(id);
                                $(this).val(data[id.toString()]);
                            });
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
            <h1 class="page-title" >Replace Barcode</h1>
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
        <a href="/" class = "dropbtn">Log out</a>
        <div class="page-body" style="height: 80vh;">
            <div class="marginftn">
                <form:form method="post" action="Change-Barcode"  modelAttribute="inputNwtxdt" style="width: 75%;">
                    <div class="border rounded form-group" style="width: auto; margin-left: 20px; margin: 15px;">
                    <fieldset>
                        <span class="col-xs-4">
                            <label>Book Code</label>
                            <input id="bookCode"
                                    type="text"
                                   name="bookCode"
                                   class="form-control"/>
                        </span>
                        <span class="col-xs-4">
                            <label>Book Year</label>
                            <input id="editionYear"
                                    type="text"
                                   name="editionYear"
                                   class="form-control"/></span>
                        <span class="col-xs-4">
                            <label>Strike Barcode</label>
                            <input id="barcode"
                                    type="text"
                                   name="barcode"
                                   class="form-control"/>
                        </span>
                    </fieldset>
                    <div class="tenPix"></div>
                        <fieldset id="messageChanging">
                            <legend class="legend">Book Info</legend>
                            <p>
                                <label>Title</label>
                                <input id="title"
                                        type="text"
                                       name="bookTitle"
                                       class="form-control left"/>
                            </p>
                            <p>
                                <label>Seq Nr</label>
                                <input id="seqNr"
                                        type="text"
                                       name="seqNumber"
                                       class="form-control left"/>
                            </p>
                            <p>
                                <label>Change Barcode To</label>
                                <input id="barcodeChange"
                                        type="text"
                                       name="newBarcode"
                                       class="form-control left"/>
                            </p>
                        </fieldset>
                    </div>
                    <!--
                    <span class="addlmargin">
                        <input type="submit" class="btn btn-primary btnCol" name="Save">
                        <button type="submit" class="btn btn-primary btnCol" name="clear">Clear</button>
                    </span>
                    -->
                    <p>
                        <button type="button" id="makeChanges" class="btn btn-primary btnCol">Save</button>
                    </p>
                    <p>
                        <button type="button" id="clear" class="btn btn-primary btnCol">Clear</button>
                    </p>
                </form:form>
            </div>
        </div>
    </body>
</html>