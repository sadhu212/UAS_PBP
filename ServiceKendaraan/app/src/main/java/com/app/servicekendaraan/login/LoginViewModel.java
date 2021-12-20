package com.app.servicekendaraan.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.servicekendaraan.roomdatabase.ServiceKendaraanDatabase;
import com.app.servicekendaraan.roomdatabase.user.UserDao;
import com.app.servicekendaraan.roomdatabase.user.UserData;

public class LoginViewModel extends AndroidViewModel {
    private UserDao dao;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        dao = ServiceKendaraanDatabase.getSaveInstance(application).userDao();
    }

    public LiveData<UserData> login(String username, String password) {
       return dao.getDataByUser(username, password);
    }
}
