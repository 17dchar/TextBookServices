package com.webapp.TextBook.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="NWTXCM")
public class Nwtxcm
{
    @Id
    @Column(name = "NWTXCM_COURSE")
    @NotEmpty
    private String cmCourse;
    @Column(name = "NWTXCM_MESSAGE")
    private String cmMessage;
    @Column(name = "NWTXCM_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date acDate;

    public String getCmCourse() {
        return cmCourse;
    }

    public void setCmCourse(String cmCourse) {
        this.cmCourse = cmCourse;
    }

    public String getCmMessage() {
        return cmMessage;
    }

    public void setCmMessage(String cmMessage) {
        this.cmMessage = cmMessage;
    }

    public Date getAcDate() {
        return acDate;
    }

    public void setAcDate(Date acDate) {
        this.acDate = acDate;
    }
}
