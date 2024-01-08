package com.gapharma.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.gapharma.data.entities.Issue;

import java.util.List;

@Dao
public interface IssueDao {
    @Insert
    void save(Issue issue);

    @Query("SELECT * FROM issue")
    List<Issue> findAll();

//    @Transaction
//    @Query("SELECT * FROM issue WHERE id = :issueId")
//    IssueWithStates getIssueWithStates(Long issueId);
}
