package com.example.demo_quanlyhappy.DTO;

import java.util.Date;

public class HoaDonVe {
    int idHDve;
    int idKH;
    int tienTTve;
    Date tgTTve;
    int goiSD;

    public HoaDonVe() {
    }

    public HoaDonVe(int idHDve, int idKH, int tienTTve, Date tgTTve, int goiSD) {
        this.idHDve = idHDve;
        this.idKH = idKH;
        this.tienTTve = tienTTve;
        this.tgTTve = tgTTve;
        this.goiSD = goiSD;
    }

    public int getIdHDve() {
        return idHDve;
    }

    public void setIdHDve(int idHDve) {
        this.idHDve = idHDve;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public int getTienTTve() {
        return tienTTve;
    }

    public void setTienTTve(int tienTTve) {
        this.tienTTve = tienTTve;
    }

    public Date getTgTTve() {
        return tgTTve;
    }

    public void setTgTTve(Date tgTTve) {
        this.tgTTve = tgTTve;
    }

    public int getGoiSD() {
        return goiSD;
    }

    public void setGoiSD(int goiSD) {
        this.goiSD = goiSD;
    }

    public HoaDonVe(int idKH, int tienTTve, Date tgTTve, int goiSD) {
        this.idKH = idKH;
        this.tienTTve = tienTTve;
        this.tgTTve = tgTTve;
        this.goiSD = goiSD;
    }
}
