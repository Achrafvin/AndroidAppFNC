package com.gapharma.data.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.gapharma.data.entities.Issue;
import com.gapharma.data.entities.IssueState;

import java.util.List;

public class IssueWithStates {
    @Embedded
    public Issue issue;

    @Relation(
            parentColumn = "id",
            entityColumn = "issueId"
    )
    public List<IssueState> issueStates;
}
