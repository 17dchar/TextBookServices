package com.webapp.TextBook.Model;

import java.io.Serializable;

public class AddBookModel implements Serializable{

    //Variables for AddBook site
    private String bookYear;
    private String bookCode;


    public String getBookYear() {
        return bookYear;
    }

    public void setBookYear(String bookYear) {
        this.bookYear = bookYear;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

}
