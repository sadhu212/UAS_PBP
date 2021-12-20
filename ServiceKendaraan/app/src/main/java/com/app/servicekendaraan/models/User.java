package com.app.servicekendaraan.models;
public class User {
    private Long id;
    private String nama_lengkap;
    private int umur;
    private String alamat;
    private int no_telepon;
    private String email;

    public User(String nama_lengkap, int umur, String alamat, int no_telepon, String email) {
        this.nama_lengkap = nama_lengkap;
        this.umur = umur;
        this.alamat = alamat;
        this.no_telepon = no_telepon;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(int no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
