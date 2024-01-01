package com.ambapharm.data.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.ambapharm.data.entities.Issue;
import com.ambapharm.data.entities.IssueType;

public class IssueWithType {
    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "issueTypeId",
            entityColumn = "id"
    )
    public IssueType issueType;
}
