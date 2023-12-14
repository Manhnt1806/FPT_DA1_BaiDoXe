package com.example.demo_quanlyhappy.DTO;

import java.util.Date;

public class NapTien {
    private int idNT;
    private int idKH;
    private int htttNT;
    private int soTien;
    Date ngayNap;
    private int statusNT;

    public NapTien() {
    }

    public NapTien(int idNT, int idKH, int htttNT, int soTien, Date ngayNap, int statusNT) {
        this.idNT = idNT;
        this.idKH = idKH;
        this.htttNT = htttNT;
        this.soTien = soTien;
        this.ngayNap = ngayNap;
        this.statusNT = statusNT;
    }

    public NapTien(int idKH, int htttNT, int soTien, Date ngayNap, int statusNT) {
        this.idKH = idKH;
        this.htttNT = htttNT;
        this.soTien = soTien;
        this.ngayNap = ngayNap;
        this.statusNT = statusNT;
    }

    public int getIdNT() {
        return idNT;
    }

    public void setIdNT(int idNT) {
        this.idNT = idNT;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public int getHtttNT() {
        return htttNT;
    }

    public void setHtttNT(int htttNT) {
        this.htttNT = htttNT;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public Date getNgayNap() {
        return ngayNap;
    }

    public void setNgayNap(Date ngayNap) {
        this.ngayNap = ngayNap;
    }

    public int getStatusNT() {
        return statusNT;
    }

    public void setStatusNT(int statusNT) {
        this.statusNT = statusNT;
    }
}
