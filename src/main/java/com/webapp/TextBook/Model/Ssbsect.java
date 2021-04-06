package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="SSBSECT")
public class Ssbsect {

    @Column(name = "SSBSECT_TERM_CODE")
    @NotEmpty
    private String termCode;
    @Column(name = "SSBSECT_CRN")
    @NotEmpty
    @Id
    private String crn;
    @Column(name = "SSBSECT_PTRM_CODE")
    private String ptrmCode;
    @Column(name = "SSBSECT_SUBJ_CODE")
    @NotEmpty
    private String subjCode;
    @Column(name = "SSBSECT_CRSE_NUMB")
    @NotEmpty
    private String crseNumb;
    @Column(name = "SSBSECT_SEQ_NUMB")
    @NotEmpty
    private String seqNumb;

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getPtrmCode() {
        return ptrmCode;
    }

    public void setPtrmCode(String ptrmCode) {
        this.ptrmCode = ptrmCode;
    }

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

    public String getSeqNumb() {
        return seqNumb;
    }

    public void setSeqNumb(String seqNumb) {
        this.seqNumb = seqNumb;
    }
}
    