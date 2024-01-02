package com.ambapharm.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.ambapharm.data.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void save(User user);
    @Query("SELECT * FROM user")
    List<User> findAll();

    @Query("SELECT * FROM User WHERE name = :name")
    User findByName(String name);

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User findByIdentityAndPassword(String email, String password);

    @Query("SELECT * FROM User WHERE id = :id")
    User findById(Long id);

}
