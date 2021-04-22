<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Schedule</title>
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
            <h1 class="page-title">Student Schedule</h1>
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
        <a href="/" class = "dropbtn">Log out</a>
        <div class="page-body" style="height: 80vh;">
            <div class="tenPix"></div>
            <div class="container">
                <div class="container">
                    <form class="yearCodeForm" method="post">
                        <fieldset>
                            <p>
                                <label>Terms:</label>
                                <select name="selectedTerm">
                                    <option value="">Select from List</option>
                                    <c:forEach var="description" items="${term}">
                                        <option value="${description.getCode()}">${description.getDesc()}</option>
                                    </c:forEach>
                                </select>
                            </p>
                        </fieldset>
                        <fieldset>
                            <p>
                                <div class="form-group">
                                    <label>ID:</label>
                                    <input type = "text"
                                           name = "id"
                                           class="form-control fiftyWidth"/>
                                </div>
                            </p>
                                <input type="submit" name="Save"/>
                        </fieldset>
                        <p>
                            <button type="button" id="makeChanges" class="btn btn-primary btnCol">Save</button>
                        </p>
                        <p>
                            <button type="button" id="clear" class="btn btn-primary btnCol">Clear</button>
                        </p>
                    </form>
                    <c:forEach items="${output}" var="output" varStatus="status">
                            <p>Title: ${outputTitle[status.index]}</p>
                            <p>Meta Data: ${output.subjCode}, ${output.crseNumb}, ${output.seqNumb}, ${output.ptrmCode}</p>
                            <p>Times: ${outputTimes[status.index].monday} ${outputTimes[status.index].tuesday} ${outputTimes[status.index].wednesday}
                                    ${outputTimes[status.index].thursday} ${outputTimes[status.index].friday}</p>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>