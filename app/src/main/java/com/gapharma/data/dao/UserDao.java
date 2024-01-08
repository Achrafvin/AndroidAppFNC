package com.gapharma.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.gapharma.data.entities.User;
import com.gapharma.data.models.UserWithAccessRight;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void save(User user);
    @Query("SELECT * FROM user")
    List<User> findAll();

    @Query("SELECT * FROM user WHERE name = :name")
    User findByName(String name);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    User findByIdentityAndPassword(String email, String password);

    @Query("SELECT * FROM user WHERE cip = :cipCode")
    User findUserByCipCode(String cipCode);

    @Query("SELECT * FROM user WHERE id = :id")
    User findById(Long id);

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    UserWithAccessRight getUserWithAccessRight(Long userId);

}
