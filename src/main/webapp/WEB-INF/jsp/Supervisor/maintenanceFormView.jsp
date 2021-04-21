<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/style.css">
        <meta charset="UTF-8">
        <title>Maintenance Form</title>
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
                    } else{
                        console.log("this one");
                        obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +'"}';
                    }
                    var string = JSON.parse(obj);
                    console.log("Query With " + string);
                    $.ajax({
                        type: "POST",
                        url: '/Maintenance-Form',
                        data: string,
                        dataType: 'json',
                        timeout: 6000000,
                        success: function (data) {
                            console.log("why");
                            if (data.errors){
                                console.log(data);
                            }else if(!withEditionYear) {
                                document.getElementById('editionYear').placeholder = data.editionYear;
                            }
                            console.log(data)
                            document.getElementById('title').placeholder = data.title;
                            document.getElementById('publisher').placeholder = data.publisher;
                            document.getElementById('author').placeholder = data.author;
                            document.getElementById('bookStatus').placeholder = data.bookStatus;
                            document.getElementById('comment').placeholder = data.comment;
                            document.getElementById('firstUsedDate').placeholder = data.firstUsedDate;
                            document.getElementById('isbn').placeholder = data.isbn;
                            document.getElementById('purchaseDate').placeholder = data.purchaseDate;
                            document.getElementById('crse1').placeholder = data.course1;
                            document.getElementById('crse2').placeholder = data.course2;
                            document.getElementById('crse3').placeholder = data.course3;
                            document.getElementById('crse4').placeholder = data.course4;
                            document.getElementById('crse5').placeholder = data.course5;
                            document.getElementById('crseName').placeholder = data.courseTitle;
                            document.getElementById('booksPurchased').innerHTML = data.booksPurchased;
                            document.getElementById('booksSold').innerHTML = data.booksSold;
                            document.getElementById('booksUnrepairable').innerHTML = data.booksUnrepairable;
                            document.getElementById('booksNonreturned').innerHTML = data.booksNonreturned;
                            document.getElementById('booksInInventory').innerHTML = data.booksinInventory;
                            document.getElementById('booksCheckedOut').innerHTML = data.booksCheckedOut;
                            document.getElementById('lastSeqNo').innerHTML = data.lastSeqNr;
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
            });
        </script>
    </head>
    <body>
        <div class="TBSHeader">
            <h1 class="page-title" >Maintenance</h1>
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
        <div class = "page-body">
            <form method = post class="addlmargin">
                <div class="border rounded form-group">
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
                                   class="form-control"/>
                        </span>
                    </fieldset>
                </div>
                <div class="marginftn">
                    <div class="container">
                        <div class="row">
                            <div class="column left">
                                <fieldset>
                                    <legend>Book Info</legend>
                                    <p>
                                        <label>Title:</label>
                                        <input type="text" id="title" name ="title" class="form-control width85">
                                    </p>
                                    <p>
                                        <label>Author:</label>
                                        <input type="text" id="author" name = "author" class="form-control width85">
                                    </p>
                                    <p>
                                        <label>Publisher:</label>
                                        <input type="text" id="publisher" name="publisher" class="form-control width85">
                                    </p>
                                    <p>
                                        <label>ISBN #:</label>
                                        <input type="text" id="isbn" name = "isbn" class="form-control width85">
                                        <label>Cost:</label>
                                        <input type="text" id="bookCost" class="form-control width85">
                                    </p>
                                    <p>
                                        <label>Status:</label>
                                        <input type="text" id="bookStatus" name="bookStatus" class="form-control">
                                        <label>Purchase Date:</label>
                                        <input type="date" id="purchaseDate" name="purchaseDate" class="form-control">
                                    </p>
                                    <p>
                                        <label>Discontinued:</label>
                                        <input type="text" id="discontinuedDate" name="discontinuedDate" class="form-control">
                                        <label>First Used Date:</label>
                                        <input type="date" id="firstUsedDate" name="firstUsedDate" class="form-control">
                                    </p>
                                    <p>
                                        <label>Date Comment:</label>
                                        <input type="text" id="comment" name="comment" class="form-control">
                                    </p>
                                </fieldset>
                                <fieldset>
                                    <legend>Course Info</legend>
                                    <p>
                                        <label>Course Title:</label>
                                        <input type="text" id="crseName" name="crseName" class="form-control">
                                    </p>
                                    <p>
                                        <label>Course 1:</label>
                                        <input type="text" id="crse1" name="crse1" class="form-control">
                                        <label>Course 4:</label>
                                        <input type="text" id="crse4" name="crse4" class="form-control">
                                    </p>
                                    <p>
                                        <label>Course 2:</label>
                                        <input type="text" id="crse2" name="crse2" class="form-control">
                                        <label>Course 5:</label>
                                        <input type="text" id="crse5" name="crse5" class="form-control">
                                    </p>
                                    <p>
                                        <label>Course 3:</label>
                                        <input type="text" id="crse3" name="crse3" class="form-control">
                                    </p>
                                </fieldset>
                            </div>
                            <div class="column middle">
                                <fieldset>
                                    <legend>Book Stats</legend>
                                    <p>
                                        <label>Books Purchased:</label>
                                        <p type="number" id="booksPurchased" value="0" class="form-control">
                                    </p>
                                    <p>
                                        <label>Books Sold:</label>
                                        <p type="number" id="booksSold" value="0" class="form-control">
                                    </p>
                                    <p>
                                        <label>Books Unrepairable:</label>
                                        <p type="number" id="booksUnrepairable" value="0" class="form-control">
                                    </p>
                                    <p>
                                        <label>Books Nonreturned:</label>
                                        <p type="number" id="booksNonreturned" value="0" class="form-control">
                                    </p>
                                    <p>
                                        <label>Books In Inventory:</label>
                                        <p type="number" id="booksInInventory" value="0" class="form-control">
                                    </p>
                                    <p>
                                        <label>Books Checked Out:</label>
                                        <p type="number" id="booksCheckedOut" value="0" class="form-control">
                                    </p>
                                    <p>
                                        <label>Last Seq. No.:</label>
                                        <p type="number" u0="" id="lastSeqNo" value="0" class="form-control">
                                    </p>
                                </fieldset>
                            </div>
                            <div class="column right">
                                <p>
                                    <button type="button" class="btn btn-primary btnCol">Save</button>
                                </p>
                                <p>
                                    <button type="button" class="btn btn-primary btnCol">Clear</button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>