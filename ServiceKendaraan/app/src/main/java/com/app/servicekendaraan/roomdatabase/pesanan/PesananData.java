package com.app.servicekendaraan.roomdatabase.pesanan;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pesanan_data")
public class PesananData {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "tanggal")
    private String tanggal;
    @ColumnInfo(name = "alamat")
    private String alamat;
    @ColumnInfo(name = "alamat_maps")
    private String alamatMaps;
    @ColumnInfo(name = "harga")
    private String harga;
    @ColumnInfo(name = "metode_pembayaran")
    private String metodePembayaran;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamatMaps() {
        return alamatMaps;
    }

    public void setAlamatMaps(String alamatMaps) {
        this.alamatMaps = alamatMaps;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}

