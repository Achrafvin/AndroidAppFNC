package com.ambapharm.data.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.ambapharm.data.entities.Issue;
import com.ambapharm.data.entities.IssueState;

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
