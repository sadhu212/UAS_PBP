package com.app.servicekendaraan.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.servicekendaraan.roomdatabase.pesanan.PesananDao;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.roomdatabase.profile.ProfilDao;
import com.app.servicekendaraan.roomdatabase.profile.ProfileData;
import com.app.servicekendaraan.roomdatabase.user.UserDao;
import com.app.servicekendaraan.roomdatabase.user.UserData;

@Database(entities = {UserData.class, ProfileData.class, PesananData.class}, version = 1, exportSchema = false)
public abstract class ServiceKendaraanDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ProfilDao profileDao();
    public abstract PesananDao pesananDao();

    static ServiceKendaraanDatabase INSTANCE;

    public static ServiceKendaraanDatabase getSaveInstance(final Context context){
        if (INSTANCE == null) {
            synchronized (ServiceKendaraanDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ServiceKendaraanDatabase.class, "service_kendaraan.db").build();
                }
            }
        }
        return INSTANCE;
    }
}
