package com.example.demo_quanlyhappy.DTO;

import java.io.Serializable;

public class KhachHang implements Serializable {
    private int idKH;
    private String cccdKH;
    private String sdtKH;
    private String passKH;
    private String tenKH;
    private int soDuKH;
    private int goiSD;

    public KhachHang() {
    }

    public KhachHang(int idKH, String cccdKH, String sdtKH, String passKH, String tenKH, int soDuKH, int goiSD) {
        this.idKH = idKH;
        this.cccdKH = cccdKH;
        this.sdtKH = sdtKH;
        this.passKH = passKH;
        this.tenKH = tenKH;
        this.soDuKH = soDuKH;
        this.goiSD = goiSD;
    }

    public KhachHang(String cccdKH, String sdtKH, String passKH, String tenKH, int soDuKH, int goiSD) {
        this.cccdKH = cccdKH;
        this.sdtKH = sdtKH;
        this.passKH = passKH;
        this.tenKH = tenKH;
        this.soDuKH = soDuKH;
        this.goiSD = goiSD;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getCccdKH() {
        return cccdKH;
    }

    public void setCccdKH(String cccdKH) {
        this.cccdKH = cccdKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getPassKH() {
        return passKH;
    }

    public void setPassKH(String passKH) {
        this.passKH = passKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSoDuKH() {
        return soDuKH;
    }

    public void setSoDuKH(int soDuKH) {
        this.soDuKH = soDuKH;
    }

    public int getGoiSD() {
        return goiSD;
    }

    public void setGoiSD(int goiSD) {
        this.goiSD = goiSD;
    }
}
