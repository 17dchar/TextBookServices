package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="SCBCRSE")
public class Scbcrse {
    @Id
    @Column(name = "SCBCRSE_SUBJ_CODE")
    @NotEmpty
    private String subjCode;
    @Column(name = "SCBRSE_CRSE_NUMB")
    @NotEmpty
    private String crseNumb;
    @Column(name = "SCBRSE_EFF_TERM")
    private String effTerm;
    @Column(name = "SCBCRSE_TITLE")
    private String title;


    public String getSubjCode() {
        return subjCode;
    }

    public void setSubjCode(String subjCode) {
        this.subjCode = subjCode;
    }

    public String getCrseNumb() {
        return crseNumb;
    }

    public void setCrseNumb(String crseNumb) {
        this.crseNumb = crseNumb;
    }

    public String getEffTerm() {
        return effTerm;
    }

    public void setEffTerm(String effTerm) {
        this.effTerm = effTerm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
