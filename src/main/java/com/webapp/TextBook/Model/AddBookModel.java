package com.webapp.TextBook.Model;

import java.io.Serializable;

public class AddBookModel implements Serializable{

    //Variables for AddBook site
    private String bookYear;
    private String bookCode;
    private String bookTitle;
    private String seqNr;

    public AddBookModel(String bookYear, String bookCode, String bookTitle, String seqNr) {
        this.bookYear = bookYear;
        this.bookCode = bookCode;
        this.bookTitle = bookTitle;
        this.seqNr = seqNr;
    }

    public AddBookModel(){
        this.bookYear = "work";
        this.bookCode = "work";
        this.bookTitle = "work";
        this.seqNr = "work";
    }
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }


    public String getSeqNr() {
        return seqNr;
    }

    public void setSeqNr(String seqNr) {
        this.seqNr = seqNr;
    }



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
