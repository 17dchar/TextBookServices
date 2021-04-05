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
    private String course;
    @Column(name = "NWTXCM_MESSAGE")
    private String message;
    @Column(name = "NWTXCM_ACTIVITY_DATE")
    @Temporal(TemporalType.DATE)
    private Date acDate;

    public String getCmCourse() {
        return course;
    }

    public void setCmCourse(String cmCourse) {
        this.course = cmCourse;
    }

    public String getCmMessage() {
        return message;
    }

    public void setCmMessage(String cmMessage) {
        this.message = cmMessage;
    }

    public Date getAcDate() {
        return acDate;
    }

    public void setAcDate(Date acDate) {
        this.acDate = acDate;
    }
}
