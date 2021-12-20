package com.app.servicekendaraan.roomdatabase.profile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_data")
public class ProfileData {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "tanggal")
    String tanggal;
    @ColumnInfo(name = "name_lengkap")
    String namaLengkap;
    @ColumnInfo(name = "alamat")
    String alamat;
    @ColumnInfo(name = "email")
    String email;
    @ColumnInfo(name = "umur")
    String umur;
    @ColumnInfo(name = "no_telepon")
    String noTelepon;

    public ProfileData(int id, String tanggal, String namaLengkap, String alamat, String email, String umur, String noTelepon) {
        this.id = id;
        this.tanggal = tanggal;
        this.namaLengkap = namaLengkap;
        this.alamat = alamat;
        this.email = email;
        this.umur = umur;
        this.noTelepon = noTelepon;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
