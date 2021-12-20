package com.app.servicekendaraan.roomdatabase.profile;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfilDao {
    @Insert
    void insertProfil(ProfileData profileData);

    @Update
    void updateProfil(ProfileData profileData);

    @Delete
    void deleteProfil(ProfileData profileData);

    @Query("SELECT * FROM profile_data")
    LiveData<List<ProfileData>> getAllProfilData();

    @Query("SELECT * FROM profile_data WHERE id=:id")
    LiveData<List<ProfileData>> getDataById(int id);
}
