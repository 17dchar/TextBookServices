package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table()
public class OutputBookInformationModel
{
    Boolean errors;
    String errorMessage;
    String bookCode;
    String editionYear;
    @Id
    String barcode;
    String title;
    String seqNr;
    String currentDisposition;
    String currentTermCheckOut;
    String currentCheckedOutTo;
    String currentDateCheckedOut;
    String previousTermCheckedOut;
    String previousCheckedOutTo;
    String previousDateCheckedIn;
    int booksPurchased;
    int booksSold;
    int booksUnrepairable;
    int booksNonreturned;
    int booksinInventory;
    int booksCheckedOut;

    public int getBooksPurchased() {
        return booksPurchased;
    }

    public void setBooksPurchased(int booksPurchased) {
        this.booksPurchased = booksPurchased;
    }

    public int getBooksSold() {
        return booksSold;
    }

    public void setBooksSold(int booksSold) {
        this.booksSold = booksSold;
    }

    public int getBooksUnrepairable() {
        return booksUnrepairable;
    }

    public void setBooksUnrepairable(int booksUnrepairable) {
        this.booksUnrepairable = booksUnrepairable;
    }

    public int getBooksNonreturned() {
        return booksNonreturned;
    }

    public void setBooksNonreturned(int booksNonreturned) {
        this.booksNonreturned = booksNonreturned;
    }

    public int getBooksinInventory() {
        return booksinInventory;
    }

    public void setBooksinInventory(int booksinInventory) {
        this.booksinInventory = booksinInventory;
    }

    public int getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public void setBooksCheckedOut(int booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }

    public int getBooksCheckedIn() {
        return booksCheckedIn;
    }

    public void setBooksCheckedIn(int booksCheckedIn) {
        this.booksCheckedIn = booksCheckedIn;
    }

    public int getLastSeqNr() {
        return lastSeqNr;
    }

    public void setLastSeqNr(int lastSeqNr) {
        this.lastSeqNr = lastSeqNr;
    }

    int booksCheckedIn;
    int lastSeqNr;

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getEditionYear() {
        return editionYear;
    }

    public void setEditionYear(String editionYear) {
        this.editionYear = editionYear;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeqNr() {
        return seqNr;
    }

    public void setSeqNr(String seqNr) {
        this.seqNr = seqNr;
    }

    public String getCurrentDisposition() {
        return currentDisposition;
    }

    public void setCurrentDisposition(String currentDisposition) {
        this.currentDisposition = currentDisposition;
    }

    public String getCurrentTermCheckOut() {
        return currentTermCheckOut;
    }

    public void setCurrentTermCheckOut(String currentTermCheckOut) {
        this.currentTermCheckOut = currentTermCheckOut;
    }

    public String getCurrentCheckedOutTo() {
        return currentCheckedOutTo;
    }

    public void setCurrentCheckedOutTo(String currentCheckedOutTo) {
        this.currentCheckedOutTo = currentCheckedOutTo;
    }

    public String getCurrentDateCheckedOut() {
        return currentDateCheckedOut;
    }

    public void setCurrentDateCheckedOut(String currentDateCheckedOut) {
        this.currentDateCheckedOut = currentDateCheckedOut;
    }

    public String getPreviousTermCheckedOut() {
        return previousTermCheckedOut;
    }

    public void setPreviousTermCheckedOut(String previousTermCheckedOut) {
        this.previousTermCheckedOut = previousTermCheckedOut;
    }

    public String getPreviousCheckedOutTo() {
        return previousCheckedOutTo;
    }

    public void setPreviousCheckedOutTo(String previousCheckedOutTo) {
        this.previousCheckedOutTo = previousCheckedOutTo;
    }

    public String getPreviousDateCheckedIn() {
        return previousDateCheckedIn;
    }

    public void setPreviousDateCheckedIn(String previousDateCheckedIn) {
        this.previousDateCheckedIn = previousDateCheckedIn;
    }

    public Boolean getErrors() {
        return errors;
    }

    public void setErrors(Boolean errors) {
        this.errors = errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
