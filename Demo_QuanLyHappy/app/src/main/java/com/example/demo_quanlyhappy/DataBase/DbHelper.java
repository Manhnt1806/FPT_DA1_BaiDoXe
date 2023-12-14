package com.example.demo_quanlyhappy.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "Happy_Lib";
    static final int DB_VERSION = 29;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_khachHang(idKH INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, cccdKH TEXT NOT NULL UNIQUE, sdtKH TEXT NOT NULL UNIQUE, passKH TEXT NOT NULL, tenKH TEXT NOT NULL, soDuKH INTEGER NOT NULL, goiSD INTEGER NOT NULL)";
        db.execSQL(sql);
        sql = "INSERT INTO tb_khachHang VALUES(null, '034203008190', '0111111111','qqqqqq123', 'Nguyễn Thế Mạnh', '0', 0)";
        db.execSQL(sql);
        sql = "INSERT INTO tb_khachHang VALUES(null, '034203008193', '0111111113','qqqqqq123', 'Nguyễn Thị Gấm', '0', 0)";
        db.execSQL(sql);
        sql = "INSERT INTO tb_khachHang VALUES(null, '000000000000', '0000000000','qqqqqq123', 'Khách hàng', '0', 0)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_nhanVien(idNV INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,cccdNV TEXT NOT NULL UNIQUE, chucVu TEXT NOT NULL, sdtNV TEXT NOT NULL UNIQUE, passNV TEXT NOT NULL, tenNV TEXT NOT NULL, statusNV INTEGER NOT NULL)";
        db.execSQL(sql);
        sql = "INSERT INTO tb_nhanVien VALUES(null, '034203008191', 'Quản lý', '0111111110','qqqqqq123', 'Mạnh NT', 1)";
        db.execSQL(sql);
        sql = "INSERT INTO tb_nhanVien VALUES(null, '034203008192', 'Nhân viên', '0111111112','qqqqqq123', 'Nguyệt TT', 1)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_baiGuiXe(idBaiGuiXe INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, soLuongGui INTEGER NOT NULL, soLuongTrong INTEGER NOT NULL)";
        db.execSQL(sql);
        sql = "INSERT INTO tb_baiGuiXe VALUES(null, 5, 5)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_napTien(idNT INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, idKH INTEGER REFERENCES tb_khachHang(idKH), htttNT INTEGER NOT NULL, soTienNap INTEGER NOT NULL, ngayNap DATE NOT NULL, statusNT INTEGER NOT NULL)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_xe(idXe INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, idKH INTEGER REFERENCES tb_khachHang(idKH), tenXe TEXT NOT NULL, bienSoXe TEXT NOT NULL UNIQUE, dangKyXe BLOD UNIQUE, statusXe INTEGER NOT NULL," +
                "idNV INTEGER REFERENCES tb_nhanVien(idNV), statusGuiXe INTEGER NOT NULL)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_hoaDon(idHD INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, idXe INTEGER REFERENCES tb_xe(idXe), htTT INTEGER NOT NULL, tienTT INTEGER NOT NULL,idKH INTEGER REFERENCES tb_khachHang(idKH), tgVao DATE, tgRa DATE, haVao BLOD, haRa BLOD, statusHD NOT NULL)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_hoaDonVe(idHDve INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, idKH INTEGER REFERENCES tb_khachHang(idKH), tienTTve INTEGER NOT NULL, tgTTve DATE)";
        db.execSQL(sql);
        sql = "CREATE TABLE tb_Check(idCheck INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, idKH INTEGER REFERENCES tb_khachHang(idKH), idNV INTEGER REFERENCES tb_nhanVien(idNV), idNT INTEGER REFERENCES tb_napTien(idNT), tgCheck DATE)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_khachHang");
        db.execSQL("DROP TABLE IF EXISTS tb_nhanVien");
        db.execSQL("DROP TABLE IF EXISTS tb_baiGuiXe");
        db.execSQL("DROP TABLE IF EXISTS tb_napTien");
        db.execSQL("DROP TABLE IF EXISTS tb_xe");
        db.execSQL("DROP TABLE IF EXISTS tb_hoaDon");
        db.execSQL("DROP TABLE IF EXISTS tb_hoaDonVe");
        db.execSQL("DROP TABLE IF EXISTS tb_Check");
        onCreate(db);
    }
}
