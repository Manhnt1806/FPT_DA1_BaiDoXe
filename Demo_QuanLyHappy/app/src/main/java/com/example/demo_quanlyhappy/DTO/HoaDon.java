package com.example.demo_quanlyhappy.DTO;

import java.util.Date;

public class HoaDon {
    private int idHD;
    private int idGuiXe;
    private int htTT;
    private int tienTT;
    private int idKH;
    Date tgVao;
    Date tgRa;
    public byte[] haRa;
    public byte[] haVao;
    int statusHD;
    int statusGuiXe;


    public HoaDon() {
    }

    public HoaDon(int idHD, int idGuiXe, int htTT, int tienTT, int idKH, Date tgVao, Date tgRa, byte[] haRa, byte[] haVao, int statusHD, int statusGuiXe) {
        this.idHD = idHD;
        this.idGuiXe = idGuiXe;
        this.htTT = htTT;
        this.tienTT = tienTT;
        this.idKH = idKH;
        this.tgVao = tgVao;
        this.tgRa = tgRa;
        this.haRa = haRa;
        this.haVao = haVao;
        this.statusHD = statusHD;
        this.statusGuiXe = statusGuiXe;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public int getIdGuiXe() {
        return idGuiXe;
    }

    public void setIdGuiXe(int idGuiXe) {
        this.idGuiXe = idGuiXe;
    }

    public int getHtTT() {
        return htTT;
    }

    public void setHtTT(int htTT) {
        this.htTT = htTT;
    }

    public int getTienTT() {
        return tienTT;
    }

    public void setTienTT(int tienTT) {
        this.tienTT = tienTT;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public Date getTgVao() {
        return tgVao;
    }

    public void setTgVao(Date tgVao) {
        this.tgVao = tgVao;
    }

    public Date getTgRa() {
        return tgRa;
    }

    public void setTgRa(Date tgRa) {
        this.tgRa = tgRa;
    }

    public byte[] getHaRa() {
        return haRa;
    }

    public void setHaRa(byte[] haRa) {
        this.haRa = haRa;
    }

    public byte[] getHaVao() {
        return haVao;
    }

    public void setHaVao(byte[] haVao) {
        this.haVao = haVao;
    }

    public int getStatusHD() {
        return statusHD;
    }

    public void setStatusHD(int statusHD) {
        this.statusHD = statusHD;
    }

    public int getStatusGuiXe() {
        return statusGuiXe;
    }

    public void setStatusGuiXe(int statusGuiXe) {
        this.statusGuiXe = statusGuiXe;
    }

    public HoaDon(int idGuiXe, int htTT, int tienTT, int idKH, Date tgVao, Date tgRa, byte[] haRa, byte[] haVao, int statusHD, int statusGuiXe) {
        this.idGuiXe = idGuiXe;
        this.htTT = htTT;
        this.tienTT = tienTT;
        this.idKH = idKH;
        this.tgVao = tgVao;
        this.tgRa = tgRa;
        this.haRa = haRa;
        this.haVao = haVao;
        this.statusHD = statusHD;
        this.statusGuiXe = statusGuiXe;
    }
}
