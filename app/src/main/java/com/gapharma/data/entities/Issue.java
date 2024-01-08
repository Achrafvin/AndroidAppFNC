package com.gapharma.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(tableName = "issue")
public class Issue {

    @PrimaryKey
    private Long id;
    private Long issueTypeId;
    private Long carrierId;
    private Long statusId;
    private Long stateId;
    private Date creationDate;
    @Ignore
    private List<IssueState> issueStates;

    public Issue(Long id, Long issueTypeId, Long carrierId, Long statusId, Long stateId, Date creationDate) {
        this.id = id;
        this.issueTypeId = issueTypeId;
        this.carrierId = carrierId;
        this.statusId = statusId;
        this.stateId = stateId;
        this.creationDate = creationDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssueTypeId() {
        return issueTypeId;
    }

    public void setIssueTypeId(Long issueTypeId) {
        this.issueTypeId = issueTypeId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<IssueState> getIssueStates() {
        return issueStates;
    }

    public void setIssueStates(List<IssueState> issueStates) {
        this.issueStates = issueStates;
    }
}
