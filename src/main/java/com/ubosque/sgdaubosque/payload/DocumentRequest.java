package com.ubosque.sgdaubosque.payload;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class DocumentRequest {

    private String title;

    @DateTimeFormat
    private Date dateDoc;

    private String dateInsertAt;

    private String origin;

    @NotBlank
    private String userTarget;

    @NumberFormat
    private long affair;

    @NotBlank
    private String userRecieve;

    @NumberFormat
    private int docNumber;

    private String comments;

    private String annexe;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateDoc() {
        return this.dateDoc;
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
    }

    public String getDateInsertAt() {
        return this.dateInsertAt;
    }

    public void setDateInsertAt(String dateInsertAt) {
        this.dateInsertAt = dateInsertAt;
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

    public int getDocNumber() {
        return this.docNumber;
    }

    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
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
