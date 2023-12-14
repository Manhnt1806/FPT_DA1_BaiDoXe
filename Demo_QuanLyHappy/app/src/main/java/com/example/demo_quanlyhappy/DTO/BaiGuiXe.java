package com.example.demo_quanlyhappy.DTO;

public class BaiGuiXe {
    private int idBaiGuiXe;
    private int soLuongGui;
    private int soLuongTrong;

    public BaiGuiXe() {
    }

    public BaiGuiXe(int idBaiGuiXe, int soLuongGui, int soLuongTrong) {
        this.idBaiGuiXe = idBaiGuiXe;
        this.soLuongGui = soLuongGui;
        this.soLuongTrong = soLuongTrong;
    }

    public BaiGuiXe(int soLuongGui, int soLuongTrong) {
        this.soLuongGui = soLuongGui;
        this.soLuongTrong = soLuongTrong;
    }

    public int getIdBaiGuiXe() {
        return idBaiGuiXe;
    }

    public void setIdBaiGuiXe(int idBaiGuiXe) {
        this.idBaiGuiXe = idBaiGuiXe;
    }

    public int getSoLuongGui() {
        return soLuongGui;
    }

    public void setSoLuongGui(int soLuongGui) {
        this.soLuongGui = soLuongGui;
    }

    public int getSoLuongTrong() {
        return soLuongTrong;
    }

    public void setSoLuongTrong(int soLuongTrong) {
        this.soLuongTrong = soLuongTrong;
    }
}
