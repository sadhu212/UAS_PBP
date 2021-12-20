package com.app.servicekendaraan.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.servicekendaraan.roomdatabase.ServiceKendaraanDatabase;
import com.app.servicekendaraan.roomdatabase.user.UserDao;
import com.app.servicekendaraan.roomdatabase.user.UserData;

public class ProfileViewModel extends AndroidViewModel {
    private UserDao dao;

    public MutableLiveData<String> name = new MutableLiveData<>("");
    public MutableLiveData<String> umur = new MutableLiveData<>("");
    public MutableLiveData<String> alamat = new MutableLiveData<>("");
    public MutableLiveData<String> noTelp = new MutableLiveData<>("");
    public MutableLiveData<String> email = new MutableLiveData<>("");

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        dao = ServiceKendaraanDatabase.getSaveInstance(application).userDao();
    }

    public LiveData<UserData> getDataByUsername(String username) {
        return dao.getDataByUserName(username);
    }

    public void edit(UserData userData) {
        new Thread(() -> dao.updateUser(userData)).start();

    }


}
