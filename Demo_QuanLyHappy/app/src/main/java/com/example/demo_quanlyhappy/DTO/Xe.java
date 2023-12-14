package com.example.demo_quanlyhappy.DTO;

import java.util.Date;

public class Xe {
    int idXe;
    int idKH;
    String tenXe;
    String bienSoXe;
    public byte[] dangKyXe;
    int statusXe;
    int idNV;
    int statusGuiXe;

    public Xe() {
    }

    public Xe(int idXe, int idKH, String tenXe, String bienSoXe, byte[] dangKyXe, int statusXe, int idNV, int statusGuiXe) {
        this.idXe = idXe;
        this.idKH = idKH;
        this.tenXe = tenXe;
        this.bienSoXe = bienSoXe;
        this.dangKyXe = dangKyXe;
        this.statusXe = statusXe;
        this.idNV = idNV;
        this.statusGuiXe = statusGuiXe;
    }

    public Xe(int idKH, String tenXe, String bienSoXe, byte[] dangKyXe, int statusXe, int idNV, int statusGuiXe) {
        this.idKH = idKH;
        this.tenXe = tenXe;
        this.bienSoXe = bienSoXe;
        this.dangKyXe = dangKyXe;
        this.statusXe = statusXe;
        this.idNV = idNV;
        this.statusGuiXe = statusGuiXe;
    }

    public int getIdXe() {
        return idXe;
    }

    public void setIdXe(int idXe) {
        this.idXe = idXe;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getBienSoXe() {
        return bienSoXe;
    }

    public void setBienSoXe(String bienSoXe) {
        this.bienSoXe = bienSoXe;
    }

    public byte[] getDangKyXe() {
        return dangKyXe;
    }

    public void setDangKyXe(byte[] dangKyXe) {
        this.dangKyXe = dangKyXe;
    }

    public int getStatusXe() {
        return statusXe;
    }

    public void setStatusXe(int statusXe) {
        this.statusXe = statusXe;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getStatusGuiXe() {
        return statusGuiXe;
    }

    public void setStatusGuiXe(int statusGuiXe) {
        this.statusGuiXe = statusGuiXe;
    }
}
