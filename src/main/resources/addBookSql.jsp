<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<% page import="java.sql.*, java.util.*"%>

<% Class.forName("clientDriver?");%>
<%
    String bc = request.getParameter("bookCode");
    String by = request.getParameter("bookYear");
    String bt = request.getParameter("bookTitle");
    String bs = request.getParameter("seqNr");
    String bb = request.getParameter("barCode");

    Connection con = DriverManager.getConnection("localhost?");
    Statement st = con.createStatement();
    String qry = st.executeUpdate("INSERT INTO bookInfo (bookCode, bookYear, bookTitle, seqNr, barCode)
                                  VALUES ('"+bc+"', '"+by+"', '"+bt+"', '"+bs+"', '"+bb+"')";
    out.println("book added");
%>







