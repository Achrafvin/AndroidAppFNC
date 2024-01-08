package com.gapharma.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.gapharma.data.entities.AccessRight;

@Dao
public interface AccessRightDao {
    @Insert
    void save(AccessRight accessRight);

    @Query("SELECT * FROM access_right WHERE id = :id")
    AccessRight findById(Long id);

    @Query("SELECT COUNT(id) FROM access_right WHERE id = :id")
    int countById(long id);

}
