<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Course Message</title>
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

                //AJAX Function
                function queryMessage(){
                    var obj;
                    if(attempt){
                        obj = '{ "course" : "' + document.getElementById("course").value +
                            '", "message" : "' + document.getElementById("output").value + '"}';
                    } else{
                        obj = '{ "course" : "' + document.getElementById("course").value +'"}';
                    }
                    var string = JSON.parse(obj);
                    console.log("Attempting Query With " + string);
                    //AJAX
                    $.ajax({
                        type: "POST",
                        url: '/Course-Message',
                        data: string,
                        dataType: 'json',
                        timeout: 6000000,
                        success: function (data) {
                            //If there are errors, don't populate and show error
                            if(data.errors){
                                alert(data.errorMessage);
                                return;
                            }
                            if (data.course !== document.getElementById("course").value){
                                data.message = data.course;
                            }else if(!attempt){
                                attempt = true;
                            }
                            document.getElementById('output').placeholder = data.message;
                            document.getElementById('output').value = null;
                            console.log("SUCCESS");
                        },
                        error: function (data) {
                            console.log("FAILURE");
                        }
                    })
                }

                //Nullify course enter keypress
                $('#course').keydown(function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        return false;
                    }
                });

                //Initiate message query upon hitting enter in output field
                $('#output').keydown(function (e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                        queryMessage();
                        return false;
                    }
                });



                //If there is change in the course, check if it's valid to query off of
                $('#course').keyup(function(){
                    attempt = false;
                    document.getElementById('output').innerHTML =" ";
                    clearTimeout(delayman);
                    if(document.getElementById('course').value.length===9){
                        queryMessage();
                    } else{
                        //If there is no change for 3 seconds, assume that the user is waiting for
                        //The query, and query anyway (will likely get caught by data validation)
                        delayman =setTimeout(() => {
                            console.log("Checking Anyway!");
                            queryMessage();
                        }, 3000);
                    }
                });

                //Clear all inputs
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
            <h1 class="page-title" >Course Message</h1>
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
        <div class="tenPix"></div>
        <div class="page-body" style="height: 80vh;">
            <div class="container left column border rounded">
                <form:form class="courseMessageForm" method = "post" action="Course-Message" modelAttribute="inputNwtxcm">
                    <p>
                        <div class="form-group">
                            <form:label path="course">Course Number:</form:label>
                            <form:input
                                    id="course"
                                path="course"
                                class="form-control"
                                autocomplete="off"
                                placeholder=""/>
                            <form:errors path="course"></form:errors>
                        </div>
                    <div class="form-group" id = 'outputDiv'>
                        <form:label path="message">Course Message:</form:label>
                        <form:input path="message" id = 'output' class="form-control"  style="height:30px" ></form:input>
                    </div>
                    </p>
                </form:form>
                <p>
                    <button type="button" id="makeChanges" class="btn btn-primary btnCol">Save</button>
                </p>
                <p>
                    <button type="button" id="clear" class="btn btn-primary btnCol">Clear</button>
                </p>
            </div>
        </div>
    </body>
</html>