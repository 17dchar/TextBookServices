package com.webapp.TextBook.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="NWTXAR")
public class Nwtxar {
    @Id
    @Column(name = "NWTXAR_PIDM")
    private String pidm;
    @Column(name = "NWTXAR_SEQ_NO")
    private String seqNo;
    @Column(name = "NWTXAR_DETAIL_CODE")
    private String detailCode;
    @Column(name = "NWTXAR_AMOUNT")
    private String amount;
    @Column(name = "NWTXAR_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date activityDate;
    @Column(name = "NWTXAR_DOCUMENT_NO")
    private String documentNo;
    @Column(name = "NWTXAR_BILL_DATE")
    @Temporal(TemporalType.DATE)
    private Date billDate;
    @Column(name = "NWTXAR_TERM")
    private String term;

    public String getPidm() {
        return pidm;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
