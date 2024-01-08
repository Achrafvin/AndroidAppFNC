package com.gapharma.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "attachement")
public class Attachement {

    @PrimaryKey
    private Long id;
    private Long issueId;
    private String fileName;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] content;
    private Date creationDate;


    public Attachement(Long id, Long issueId, String fileName, byte[] content, Date creationDate) {
        this.id = id;
        this.issueId = issueId;
        this.fileName = fileName;
        this.content = content;
        this.creationDate = creationDate;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
