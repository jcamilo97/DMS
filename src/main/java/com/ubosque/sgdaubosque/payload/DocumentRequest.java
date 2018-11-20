package com.ubosque.sgdaubosque.payload;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

public class DocumentRequest {

    private String title;

    @DateTimeFormat
    private String dateDoc;

    private String origin;

    @NotBlank
    private String userTarget;

    @NumberFormat
    private long affair;

    @NotBlank
    private String userRecieve;

    private String comments;

    private String annexe;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateDoc() {
        return this.dateDoc;
    }

    public void setDateDoc(String dateDoc) {
        this.dateDoc = dateDoc;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUserTarget() {
        return this.userTarget;
    }

    public void setUserTarget(String userTarget) {
        this.userTarget = userTarget;
    }

    public long getAffair() {
        return this.affair;
    }

    public void setAffair(int affair) {
        this.affair = affair;
    }

    public String getUserRecieve() {
        return this.userRecieve;
    }

    public void setUserRecieve(String userRecieve) {
        this.userRecieve = userRecieve;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAnnexe() {
        return this.annexe;
    }

    public void setAnnexe(String annexe) {
        this.annexe = annexe;
    }
}
