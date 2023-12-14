package com.example.demo_quanlyhappy.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.HoaDonVe;
import com.example.demo_quanlyhappy.DataBase.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonVeDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public HoaDonVeDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<HoaDonVe> getAll(String...agrs){
        db = dbHelper.getReadableDatabase();
        ArrayList<HoaDonVe> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT k.idHDve, k.idKH, k.tienTTve, k.tgTTve, l.goiSD FROM tb_hoaDonVe k, tb_khachHang l WHERE k.idKH = l.idKH ORDER BY idHDve ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _idHDve = cs.getInt(0);
            int _idKH = cs.getInt(1);
            int _tienTTve = cs.getInt(2);
            Date _tgTTve = new Date(System.currentTimeMillis());
            try {
                _tgTTve = sdf.parse(cs.getString(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int _goiSD = cs.getInt(4);
            HoaDonVe hd = new HoaDonVe(_idHDve, _idKH, _tienTTve, _tgTTve, _goiSD);
            list.add(hd);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public ArrayList<HoaDonVe> getIDKH(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_hoaDonVe WHERE idKH=?";
        List<HoaDonVe> list = getData(sql, id);
        return (ArrayList<HoaDonVe>) list;
    }
    @SuppressLint("Range")
    private List<HoaDonVe> getData(String sql, String...SelectArgs){
        db = dbHelper.getWritableDatabase();
        List<HoaDonVe> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            HoaDonVe object = new HoaDonVe();
            object.setIdHDve(Integer.parseInt(c.getString(c.getColumnIndex("idHDve"))));
            object.setIdKH(Integer.parseInt(c.getString(c.getColumnIndex("idKH"))));
            object.setTienTTve(Integer.parseInt(c.getString(c.getColumnIndex("tienTTve"))));
            try {
                object.setTgTTve(sdf.parse(c.getString(c.getColumnIndex("tgTTve"))));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            list.add(object);
        }
        return list;
    }
    public boolean Insert(HoaDonVe obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKH", obj.getIdKH());
        values.put("tienTTve", obj.getTienTTve());
        values.put("tgTTve", sdf.format(obj.getTgTTve()));
        long row = db.insert("tb_hoaDonVe", null, values);
        return row>0;
    }
    public int DoanhThu(String tuNgay, String denNgay){
        db = dbHelper.getWritableDatabase();
        int dt = 0;
        String sqlDT = "SELECT SUM(tienTTve) as doanhThuV FROM tb_hoaDonVe WHERE tgTTve BETWEEN ? AND ?";
        Cursor cs = db.rawQuery(sqlDT, new String[]{tuNgay, denNgay});
        if (cs.getCount() != 0) {
            cs.moveToFirst();
            dt = cs.getInt(0);
        }
        return dt;
    }
}
