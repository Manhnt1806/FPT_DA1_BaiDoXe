package com.example.demosqlwithimage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "Lib";
    static final int DB_VERSION = 2;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE SanPham(ID Integer Primary Key Autoincrement, TenSP Varchar, Gia Integer, HinhMh Blob)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void Insertsanpham(String ten, Integer gia, byte[] hinh)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into SanPham values (null,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ten);
        statement.bindLong(2,gia);
        statement.bindBlob(3,hinh);
        statement.executeInsert();
    }
    void TruyVanKhongTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    Cursor TruyVanTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.rawQuery(sql,null);
    }
}
