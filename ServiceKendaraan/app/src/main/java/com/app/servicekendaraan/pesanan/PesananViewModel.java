package com.app.servicekendaraan.pesanan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.servicekendaraan.roomdatabase.ServiceKendaraanDatabase;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananDao;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;

public class PesananViewModel extends AndroidViewModel {
    private PesananDao dao;

    public PesananViewModel(@NonNull Application application) {
        super(application);
        dao = ServiceKendaraanDatabase.getSaveInstance(application).pesananDao();
    }

    public void addPesanan(PesananData data) {
        new Thread(() -> dao.insertPesanan(data)).start();
    }
}
