package com.gapharma.data.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.gapharma.data.entities.Issue;
import com.gapharma.data.entities.IssueType;

public class IssueWithType {
    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "issueTypeId",
            entityColumn = "id"
    )
    public IssueType issueType;
}
