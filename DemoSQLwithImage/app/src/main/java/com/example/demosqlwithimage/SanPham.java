package com.example.demosqlwithimage;

public class SanPham {
    String tensp;
    private Integer gia;
    byte[] hinhanh;

    SanPham(String tensp, Integer gia, byte[] hinhanh) {
        this.tensp = tensp;
        this.gia = gia;
        this.hinhanh = hinhanh;
    }
}
