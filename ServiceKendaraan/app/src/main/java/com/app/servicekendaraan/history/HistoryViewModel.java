package com.app.servicekendaraan.history;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.servicekendaraan.roomdatabase.ServiceKendaraanDatabase;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananDao;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.roomdatabase.user.UserDao;
import com.app.servicekendaraan.roomdatabase.user.UserData;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private UserDao dao;
    private PesananDao daoPesanan;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        dao = ServiceKendaraanDatabase.getSaveInstance(application).userDao();
        daoPesanan = ServiceKendaraanDatabase.getSaveInstance(application).pesananDao();
    }

    public LiveData<List<UserData>> getAllData () {
        return dao.getAllUserData();
    }
    public LiveData<List<PesananData>> getKeluhanPelangganList () {
        return daoPesanan.getAllPesanan();
    }

    public void deleteData(UserData userData) {
        new Thread(() -> {
            dao.deleteUser(userData);
        }).start();
    }

    public void deletePesanan(PesananData userData) {
        new Thread(() -> {
            daoPesanan.deleteProfil(userData);
        }).start();
    }
}
