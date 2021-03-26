package com.webapp.TextBook.Model;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="STVTERM")
public class Stvterm {
    @Id
    @Column(name = "STVTERM_CODE")
    @NotEmpty
    private String code;
    @Column(name = "STVTERM_DESC")
    @NotEmpty
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}