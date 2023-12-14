package com.example.demo_quanlyhappy.DTO;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int idNV;
    private String cccdNV;

    private String chucVu;
    private String sdtNV;
    private String passNV;
    private String tenNV;
    private int statusNV;

    public NhanVien() {
    }

    public NhanVien(int idNV, String cccdNV, String chucVu, String sdtNV, String passNV, String tenNV, int statusNV) {
        this.idNV = idNV;
        this.cccdNV = cccdNV;
        this.chucVu = chucVu;
        this.sdtNV = sdtNV;
        this.passNV = passNV;
        this.tenNV = tenNV;
        this.statusNV = statusNV;
    }

    public NhanVien(String cccdNV, String chucVu, String sdtNV, String passNV, String tenNV, int statusNV) {
        this.cccdNV = cccdNV;
        this.chucVu = chucVu;
        this.sdtNV = sdtNV;
        this.passNV = passNV;
        this.tenNV = tenNV;
        this.statusNV = statusNV;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public String getCccdNV() {
        return cccdNV;
    }

    public void setCccdNV(String cccdNV) {
        this.cccdNV = cccdNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getPassNV() {
        return passNV;
    }

    public void setPassNV(String passNV) {
        this.passNV = passNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getStatusNV() {
        return statusNV;
    }

    public void setStatusNV(int statusNV) {
        this.statusNV = statusNV;
    }
}
