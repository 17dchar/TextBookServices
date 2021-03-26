package com.webapp.TextBook.Model;

public class SCBCRSEModel {
    private String SCBCRSE_SUBJ_CODE;
    private String SCBCRSE_CRSE_NUMB;
    private String SCBCRSE_EFF_TERM;
    private String SCBCRSE_TITLE;

    public String getSCBCRSE_SUBJ_CODE() {
        return SCBCRSE_SUBJ_CODE;
    }

    public void setSCBCRSE_SUBJ_CODE(String SCBCRSE_SUBJ_CODE) {
        this.SCBCRSE_SUBJ_CODE = SCBCRSE_SUBJ_CODE;
    }

    public String getSCBCRSE_CRSE_NUMB() {
        return SCBCRSE_CRSE_NUMB;
    }

    public void setSCBCRSE_CRSE_NUMB(String SCBCRSE_CRSE_NUMB) {
        this.SCBCRSE_CRSE_NUMB = SCBCRSE_CRSE_NUMB;
    }

    public String getSCBCRSE_EFF_TERM() {
        return SCBCRSE_EFF_TERM;
    }

    public void setSCBCRSE_EFF_TERM(String SCBCRSE_EFF_TERM) {
        this.SCBCRSE_EFF_TERM = SCBCRSE_EFF_TERM;
    }

    public String getSCBCRSE_TITLE() {
        return SCBCRSE_TITLE;
    }

    public void setSCBCRSE_TITLE(String SCBCRSE_TITLE) {
        this.SCBCRSE_TITLE = SCBCRSE_TITLE;
    }


    @Override
    public String toString() {
        return "SCBCRSEModel[" +
                "SCBCRSE_SUBJ_CODE='" + SCBCRSE_SUBJ_CODE + '\'' +
                ", SCBCRSE_CRSE_NUMB='" + SCBCRSE_CRSE_NUMB + '\'' +
                ", SCBCRSE_EFF_TERM='" + SCBCRSE_EFF_TERM + '\'' +
                ", SCBCRSE_TITLE='" + SCBCRSE_TITLE + '\'' +
                ']';
    }
}
