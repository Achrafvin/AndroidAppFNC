package com.ambapharm.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.ambapharm.data.entities.Issue;
import com.ambapharm.data.models.IssueWithStates;

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
