<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<%
    String first_name=request.getParameter("bookCode");
    String last_name=request.getParameter("bookYear");
    String city_name=request.getParameter("bookTitle");
    String email=request.getParameter("seqNr");
    System.out.println("got here!");

    try
    {
        Connection connection = null;
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl.mshome.net", "textbookservices", "Textbookpassword2021");
        Statement statement = connection.createStatement();
        String command = "INSERT into users(first_name,last_name,city_name,email)values('"+first_name+"','"+last_name+"','"+city_name+"','"+email+"')";
        statement.executeUpdate(command);
        out.println("Data is successfully inserted!");
    }
    catch(Exception e)
    {
        System.out.print(e);
        e.printStackTrace();
    }
%>
