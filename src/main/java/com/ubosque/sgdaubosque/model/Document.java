package com.ubosque.sgdaubosque.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ubosque.sgdaubosque.model.audit.DateAudit;

import org.hibernate.annotations.GenericGenerator;
/**
 * Document
 */
@Entity
@Table(name = "document")
public class Document  extends DateAudit {

    private static final long serialVersionUID = 1L;

    
    @Id
    @Column(name="doc_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @Column(name="doc_title")
    private String title;

    @Column(name="doc_date")
    private String dateDoc;

    @Column(name="doc_date_settled")
    private String dateInsertAt;

    @Column(name="doc_origin")
    private String origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_target_usrid", referencedColumnName = "usr_id")
    private User userTarget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc__affair_id")
    private Affair affair;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc_user_receives_id")
    private User userRecieve;

    @Column(name="doc_number_settled")
    private int docNumber;

    @Column(name="doc_comments")
    private String comments;

    @Column(name="doc_annexed")
    private String annexe;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public User getUserTarget() {
        return this.userTarget;
    }

    public void setUserTarget(User userTarget) {
        this.userTarget = userTarget;
    }

    public Affair getAffair() {
        return this.affair;
    }

    public void setAffair(Affair affair) {
        this.affair = affair;
    }

    public User getUserRecieve() {
        return this.userRecieve;
    }

    public void setUserRecieve(User userRecieve) {
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