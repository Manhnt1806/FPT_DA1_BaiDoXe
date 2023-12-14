package com.example.demo_quanlyhappy.DTO;

import java.util.Date;

public class GuiXe {
    private int idGuiXe;
    private int idXe;
    private int idBaiGuiXe;
    private int idNV;
    private Date tgVao;
    private Date tgRa;
    private byte[] haRa;
    private byte[] haVao;
    private int statusGuiXe;

    public GuiXe() {
    }

    public GuiXe(int idGuiXe, int idXe, int idBaiGuiXe, int idNV, Date tgVao, Date tgRa, byte[] haRa, byte[] haVao, int statusGuiXe) {
        this.idGuiXe = idGuiXe;
        this.idXe = idXe;
        this.idBaiGuiXe = idBaiGuiXe;
        this.idNV = idNV;
        this.tgVao = tgVao;
        this.tgRa = tgRa;
        this.haRa = haRa;
        this.haVao = haVao;
        this.statusGuiXe = statusGuiXe;
    }

    public GuiXe(int idXe, int idBaiGuiXe, int idNV, Date tgVao, Date tgRa, byte[] haRa, byte[] haVao, int statusGuiXe) {
        this.idXe = idXe;
        this.idBaiGuiXe = idBaiGuiXe;
        this.idNV = idNV;
        this.tgVao = tgVao;
        this.tgRa = tgRa;
        this.haRa = haRa;
        this.haVao = haVao;
        this.statusGuiXe = statusGuiXe;
    }

    public int getIdGuiXe() {
        return idGuiXe;
    }

    public void setIdGuiXe(int idGuiXe) {
        this.idGuiXe = idGuiXe;
    }

    public int getIdXe() {
        return idXe;
    }

    public void setIdXe(int idXe) {
        this.idXe = idXe;
    }

    public int getIdBaiGuiXe() {
        return idBaiGuiXe;
    }

    public void setIdBaiGuiXe(int idBaiGuiXe) {
        this.idBaiGuiXe = idBaiGuiXe;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
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

    public int getStatusGuiXe() {
        return statusGuiXe;
    }

    public void setStatusGuiXe(int statusGuiXe) {
        this.statusGuiXe = statusGuiXe;
    }
}
