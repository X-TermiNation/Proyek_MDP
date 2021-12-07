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

    @Query("select * from comment")
    List<Comment> getAllComment();

    @Insert
    void insertUser(User newUser);

    @Insert
    void insertComment(Comment newComment);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Insert
    void insertFavourite(Favourite favourite);

    @Query("select * from favourite where LOWER(email) = LOWER(:email)")
    List<Favourite> getAllFavourite(String email);
}
