package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from user where LOWER(email) = LOWER(:email) and password =:password")
    List<User> loginUser(String email, String password);

    @Query("select * from user")
    List<User> gettAllUser();

    @Insert
    void insertUser(User newUser);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
