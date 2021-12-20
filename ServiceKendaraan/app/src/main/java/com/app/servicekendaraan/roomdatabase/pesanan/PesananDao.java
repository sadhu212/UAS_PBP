package com.app.servicekendaraan.roomdatabase.pesanan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.servicekendaraan.roomdatabase.profile.ProfileData;

import java.util.List;

@Dao
public interface PesananDao {
    @Insert
    void insertPesanan(PesananData pesananData);

    @Query("SELECT * FROM pesanan_data")
    LiveData<List<PesananData>> getAllPesanan();

    @Query("SELECT * FROM pesanan_data WHERE id=:id")
    LiveData<PesananData> getDataById(int id);

    @Delete
    void deleteProfil(PesananData pesananData);
}
