package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="SPRIDEN")
public class Spriden {
    @Id
    @Column(name = "SPRIDEN_ID")
    @NotEmpty
    private String id;
    @Column(name = "SPRIDEN_PIDM")
    @NotEmpty
    private String pidm;
    @Column(name = "SPRIDEN_LAST_NAME")
    @NotEmpty
    private String lastName;
    @Column(name = "SPRIDEN_FIRST_NAME")
    private String firstName;
    @Column(name = "SPRIDEN_MI")
    private String middleInitial;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPidm() {
        return pidm;
    }

    public void setPidm(String pidm) {
        this.pidm = pidm;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }



}
