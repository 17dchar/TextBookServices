<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/style.css">
        <meta charset="UTF-8">
        <title>Book Disposition</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            var delayman;
            $(document).ready(function() {
                var withEditionYear = false;
                var withBarcode = false;
                function queryMessage(){
                    var obj;
                    if(withBarcode) {
                        obj = '{ "barcode" : "' + document.getElementById("barcode").value +'"}';
                    } else if(withEditionYear){
                        obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +
                            '", "editionYear" : "' + document.getElementById("editionYear").value + '"}';
                    } else{
                        obj = '{ "bookCode" : "' + document.getElementById("bookCode").value +'"}';
                    }
                    var string = JSON.parse(obj);
                    console.log("Query With " + string);
                    $.ajax({
                        type: "POST",
                        url: '/Change-Disposition',
                        data: string,
                        dataType: 'json',
                        timeout: 6000000,
                        success: function (data) {
                            console.log(data);
                            if(data.errors){
                                alert(data.errorMessage);
                                return;
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
                            if(data.currentDisposition === "I"){
                                document.getElementById('currentDisposition').innerHTML = "Checked In";
                            } else if(data.currentDisposition === "O"){
                                document.getElementById('currentDisposition').innerHTML = "Checked Out";
                            } else if(data.currentDisposition === "S"){
                                document.getElementById('currentDisposition').innerHTML = "Sold";
                            } else if(data.currentDisposition === "N"){
                                document.getElementById('currentDisposition').innerHTML = "Not Returned";
                            } else if(data.currentDisposition === "U"){
                                document.getElementById('currentDisposition').innerHTML = "Unrepairable";
                            } else if(data.currentDisposition === null){
                                document.getElementById('currentDisposition').innerHTML = "No Disposition Given";
                            } else{
                                document.getElementById('currentDisposition').innerHTML = "Error: Disposition Value Doesn't Fit Known Dispotions Values";

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
            <h1 class="page-title" >Change Book Disposition</h1>
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
        <div class="page-body" style="height: 80vh;">
            <div class="marginftn">
                <form method="post" style="width: 75%;">
                    <div class="border rounded form-group" style="width: auto; margin-left: 20px; margin: 15px;">
                        <fieldset>
                            <span class="col-xs-4">
                                <label>Book Code</label>
                                <input type="text"
                                       id="bookCode"
                                       name="bookCode"
                                       class="form-control"/>
                            </span>
                            <span class="col-xs-4">
                                <label>Book Year</label>
                                <input type="text"
                                   id="editionYear"
                                   name="editionYear"
                                   class="form-control"/>
                            </span>
                            <span class="col-xs-4">
                                <label>Strike Barcode</label>
                                <input type="text"
                                       id="barcode"
                                       name="barcode"
                                       class="form-control"/>
                            </span>
                            </p>
                        </fieldset>
                        <div class="tenPix"></div>
                        <fieldset>
                            <legend class="legend">Book Info</legend>
                            <p>
                                <label>Title</label>
                                <p type="text"
                                       id="title"
                                       name="title"
                                   class="form-control left"> </p>
                            </p>
                            <p>
                                <label>Seq Nr</label>
                                <p type="number"
                                       id="seqNr"
                                       name="seqNr"
                                   class="form-control left"> </p>
                            </p>
                            <p>
                                <label>Current Disposition:</label>
                                <p type="text"
                                       id="currentDisposition"
                                       class="form-control left">
                            </p>
                            <p>
                                <label for="currentDisposition">Change Disposition To:</label>
                                <select name="disposition" class="custom-select select">
                                    <option selected value="" class="custom-option">(No Change)</option>
                                    <option value="I" class="custom-option">Checked In</option>
                                    <option value="O" class="custom-option">Checked Out</option>
                                    <option value="S" class="custom-option">Sold</option>
                                    <option value="N" class="custom-option">Not Retruned</option>
                                    <option value="U">Unrepairable</option>
                                </select>
                            </p>
                        </fieldset>
                    </div>
                    <span class="addlmargin">
                        <p>
                            <button type="button" id="makeChanges" class="btn btn-primary btnCol">Save</button>
                        </p>
                        <p>
                            <button type="button" id="clear" class="btn btn-primary btnCol">Clear</button>
                        </p>
                    </span>
                </form>
            </div>
        </div>
    </body>
</html>