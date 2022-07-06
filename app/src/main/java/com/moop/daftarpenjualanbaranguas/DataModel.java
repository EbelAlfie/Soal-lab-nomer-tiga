package com.moop.daftarpenjualanbaranguas;

public class DataModel {
    private String nama, harga, tanggal, kuantiti;
    public DataModel(){

    }

    public DataModel(String nama, String harga, String tanggal, String kuantiti) {
        this.nama = nama;
        this.harga = harga;
        this.tanggal = tanggal;
        this.kuantiti = kuantiti ;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(String kuantiti) {
        this.kuantiti = kuantiti;
    }
}
