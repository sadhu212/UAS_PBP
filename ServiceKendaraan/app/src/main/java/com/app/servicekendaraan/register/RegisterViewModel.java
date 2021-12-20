package com.app.servicekendaraan.register;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.servicekendaraan.roomdatabase.ServiceKendaraanDatabase;
import com.app.servicekendaraan.roomdatabase.user.UserDao;
import com.app.servicekendaraan.roomdatabase.user.UserData;

public class RegisterViewModel extends AndroidViewModel {
    private UserDao dao;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        dao = ServiceKendaraanDatabase.getSaveInstance(application).userDao();
    }

    public void addUser(UserData userData) {
        new Thread(() -> dao.insertUser(userData)).start();
    }

    public LiveData<UserData> getDataByUsername(String username) {
        return dao.getDataByUserName(username);
    }
}
