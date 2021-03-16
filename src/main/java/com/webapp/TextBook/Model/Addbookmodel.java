package com.webapp.TextBook.Model;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Addbookmodel extends HttpServlet{

    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookCode = request.getParameter("bookCode");
        String bookYear = request.getParameter("bookYear");
        if(bookCode.isEmpty() || bookYear.isEmpty())
        {
            RequestDispatcher req = request.getRequestDispatcher("addBook.jsp");
            req.include(request, response);
        }else
        {
            RequestDispatcher req = request.getRequestDispatcher("bookDisposition.jsp");
            req.forward(request, response);
        }
    }
}
