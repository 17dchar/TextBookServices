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
    <!--
    <script>
        let bookData = [
        { title: 'book1', code: '1010', year: '2000'},
        { title: 'book2', code: '1020', year: '2000'},
        { title: 'book3', code: '1030', year: '2000'},
        { title: 'database thing here', code: 'Durban', year: 'do the'}
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
    -->
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
<form method="post">
    <fieldset>
        <p>
            <label>Course:</label>
            <input type = "text"
                   name = "subjCode" />
            <label>Number:</label>
            <input type = "text"
                   name = "crseNumb" />
        </p>
        <p>
            <input type="submit" name="Save"/>
            <button type="button">Clear</button>
        </p>
    </fieldset>
</form>
<p>
    ${returnVoidError}
    ${crseNumb}
</p>
<div id = 'tableDiv'>
<table>
    <thead>
        <tr>
            <th>Course Code</th>
            <th>Course Year</th>
            <th>Title</th>
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