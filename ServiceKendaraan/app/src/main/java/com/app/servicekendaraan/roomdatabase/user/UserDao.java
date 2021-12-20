package com.app.servicekendaraan.roomdatabase.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserData userData);

    @Update
    void updateUser(UserData userData);

    @Delete
    void deleteUser(UserData userData);

    @Query("SELECT * FROM user_data")
    LiveData<List<UserData>> getAllUserData();

    @Query("SELECT * FROM user_data WHERE id=:id")
    LiveData<List<UserData>> getDataById(int id);

    @Query("SELECT * FROM user_data WHERE username=:username and password=:password")
    LiveData<UserData> getDataByUser(String username, String password);

    @Query("SELECT * FROM user_data WHERE username=:username")
    LiveData<UserData> getDataByUserName(String username);
}
