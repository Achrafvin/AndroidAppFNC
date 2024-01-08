package com.gapharma.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.gapharma.data.entities.Attachement;

import java.util.List;

@Dao
public interface AttachementDao {

    @Insert
    void save(Attachement attachement);

    @Query("SELECT * FROM attachement")
    List<Attachement> findAll();
}
