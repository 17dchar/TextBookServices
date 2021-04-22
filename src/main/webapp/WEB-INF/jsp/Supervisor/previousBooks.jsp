<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Previous Books</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            var delayman;
            $(document).ready(function() {
                var attempt = false;
                function queryMessage(){
                    var string = $("#course").serialize();
                    console.log("Attempting Query With " + string);
                    $.ajax({
                        type: "POST",
                        url: '/Course-Message',
                        data: string,
                        dataType: 'json',
                        timeout: 6000000,
                        success: function (data) {
                            if(data.errors){
                                alert(data.errorMessage);
                                return;
                            }
                            console.log(data);
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
            <h1 class="page-title">Previous Books</h1>
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
        <div class="page-body" style="">
            <div class="tenPix"></div>
                <div class="container">
                    <div class="container">
                        <form class="yearCodeForm" method = "post">
                            <fieldset>
                                <p>
                                    <div class="form-group">
                                        <label>Term:</label>
                                        <input type = "text"
                                           name = "prevTerm"
                                           class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label>ID:</label>
                                        <input type = "text"
                                           name = "id"
                                           class="form-control"/>
                                        <input type="submit" name="Find Them!"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Bag#:</label>
                                        <input type = "text"
                                           id = "bagNumber"
                                           class="form-control"/>
                                    </div>
                                </p>
                            </fieldset>
                            <c:forEach items="${prevBooks}" var="prevBooks">
                                <p>
                                <p>${prevBooks.bookCode}</p>
                                </p>
                            </c:forEach>
                            <p>
                                <button type="button" id="makeChanges" class="btn btn-primary btnCol">Save</button>
                            </p>
                            <p>
                                <button type="button" id="clear" class="btn btn-primary btnCol">Clear</button>
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>