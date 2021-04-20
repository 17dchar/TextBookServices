package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table()
public class ChangingVariableModel {
    private Boolean errors;
    private String errorMessage;
    private Boolean change;
    @Id
    private String currentBookCode;
    private String currentEditionYear;
    private String changingBookCode;
    private String changingEditionYear;


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
    public Boolean getChange() {
        return change;
    }

    public void setChange(Boolean change) {
        this.change = change;
    }

    public String getCurrentBookCode() {
        return currentBookCode;
    }

    public void setCurrentBookCode(String currentBookCode) {
        this.currentBookCode = currentBookCode;
    }

    public String getCurrentEditionYear() {
        return currentEditionYear;
    }

    public void setCurrentEditionYear(String currentEditionYear) {
        this.currentEditionYear = currentEditionYear;
    }

    public String getChangingBookCode() {
        return changingBookCode;
    }

    public void setChangingBookCode(String changingBookCode) {
        this.changingBookCode = changingBookCode;
    }

    public String getChangingEditionYear() {
        return changingEditionYear;
    }

    public void setChangingEditionYear(String changingEditionYear) {
        this.changingEditionYear = changingEditionYear;
    }

}
