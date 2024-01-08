package com.gapharma.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "issue_state")
public class IssueState {

    @PrimaryKey
    private Long id;
    private Long issueId;
    private Long stateId;
    private Long userId;
    private String comment;
    private Date modficationDate;


    public IssueState(Long id, Long issueId, Long stateId, Long userId, String comment, Date modficationDate) {
        this.id = id;
        this.issueId = issueId;
        this.stateId = stateId;
        this.userId = userId;
        this.comment = comment;
        this.modficationDate = modficationDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getModficationDate() {
        return modficationDate;
    }

    public void setModficationDate(Date modficationDate) {
        this.modficationDate = modficationDate;
    }
}

