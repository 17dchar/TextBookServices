<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Query Course</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">

    <script>
        let bookData = [
        { title: 'book1', code: '1010', year: '2014'},
        { title: 'book2', code: '1020', year: '2013'},
        { title: 'book3', code: '1030', year: '2012'},
        { title: 'book4', code: '1040', year: '2008'},
        { title: 'book5', code: '1050', year: '2007'},
        { title: 'book6', code: '1060', year: '2009'},
        { title: 'book7', code: '1070', year: '2011'},
        { title: 'book8', code: '1080', year: '2010'},
        { title: 'book9', code: '1090', year: '2020'},
        { title: 'book10', code: '1011', year: '2019'},

        ];

        window.onload = () => {
            loadTableData(bookData);
        };

        function loadTableData(bookData) {
            const tableBody = document.getElementById('tableData');
            let dataHtml = '';

        for(let book of bookData) {
            dataHtml += '<tr><td>'+book.code+'</td><td>'+book.year+'</td><td id = "tdTitle">'+book.title+'</td></tr>';
            }
            console.log(dataHtml)
            tableBody.innerHTML = dataHtml;
        }

    </script>

</head>
<body>
<h1 class="TBSHeader">Textbook Services</h1>
<h2 class="BookCodeYearTitle">Query a Course</h2>
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
<div class="border rounded form-group" style="width: 75%; margin-left: 20px; margin: 15px;">
<form method="post">
    <fieldset>
        <p>
            <label>Course:</label>
            <input type = "text"
                   name = "subjCode"
                   class="form-control"/>
            <label>Number:</label>
            <input type = "text"
                   name = "crseNumb"
                   class="form-control"/>
        </p>
        <p>
            <input type="submit" name="Save" class="btn btn-primary btnCol column"/>
            <button type="button" class="btn btn-primary btnCol column" style="margin: 5px">Clear</button>
        </p>
    </fieldset>
</form>
</div>
<p>
    ${returnVoidError}
    ${crseNumb}
</p>
<div id = 'tableDiv' class="border rounded form-group" style="width: 75%; margin-left: 20px; margin: 15px;">
<table class="table">
    <thead>
        <tr>
            <th scope="col">Course Code</th>
            <th scope="col">Course Year</th>
            <th scope="col">Title</th>
        </tr>
        <c:forEach items="${crseTable}" var="crseTable">
            <tr>
                <td>${crseTable.subjCode}</td>
                <td>${crseTable.crseNumb}</td>
                <td>${crseTable.title}</td>
            </tr>
        </c:forEach>
    </thead>
    <tbody id="tableData"></tbody>
</table>
</div>
</body>
</html>