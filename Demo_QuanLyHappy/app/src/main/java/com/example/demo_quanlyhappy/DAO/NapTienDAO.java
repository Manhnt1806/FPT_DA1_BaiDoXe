package com.example.demo_quanlyhappy.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.DataBase.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NapTienDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public NapTienDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<NapTien> getAll(String...agrs){
        db = dbHelper.getReadableDatabase();
        ArrayList<NapTien> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM tb_napTien ORDER BY idNT ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _idNT = cs.getInt(0);
            int _idKH = cs.getInt(1);
            int _htttNT = cs.getInt(2);
            int _soTienNap = cs.getInt(3);
            Date _ngayNap = new Date(System.currentTimeMillis());
            try {
                _ngayNap = sdf.parse(cs.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int _statusNT = cs.getInt(5);
            NapTien nt = new NapTien(_idNT, _idKH, _htttNT, _soTienNap, _ngayNap, _statusNT);
            list.add(nt);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public ArrayList<NapTien> getNapTien(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_napTien WHERE idKH=?";
        List<NapTien> list = getData(sql, id);
        return (ArrayList<NapTien>) list;
    }
    public ArrayList<NapTien> getCheckNT(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_napTien WHERE statusNT=?";
        List<NapTien> list = getData(sql, id);
        return (ArrayList<NapTien>) list;
    }
    public ArrayList<NapTien> tongNap(String id, String stt){
        db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM tb_napTien WHERE idKH = ? AND statusNT = ?";
        List<NapTien> list = getData(sql, new String[] {id, stt});
        return (ArrayList<NapTien>) list;
    }
    @SuppressLint("Range")
    private List<NapTien> getData(String sql, String...SelectArgs){
        db = dbHelper.getWritableDatabase();
        List<NapTien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            NapTien object = new NapTien();
            object.setIdNT(Integer.parseInt(c.getString(c.getColumnIndex("idNT"))));
            object.setIdKH(Integer.parseInt(c.getString(c.getColumnIndex("idKH"))));
            object.setHtttNT(Integer.parseInt(c.getString(c.getColumnIndex("htttNT"))));
            object.setSoTien(Integer.parseInt(c.getString(c.getColumnIndex("soTienNap"))));
            try {
                object.setNgayNap(sdf.parse(c.getString(c.getColumnIndex("ngayNap"))));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            object.setStatusNT(Integer.parseInt(c.getString(c.getColumnIndex("statusNT"))));

            list.add(object);
        }
        return list;
    }

    public boolean Insert(NapTien obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKH", obj.getIdKH());
        values.put("htttNT", obj.getHtttNT());
        values.put("soTienNap", obj.getSoTien());
        values.put("ngayNap", sdf.format(obj.getNgayNap()));
        values.put("statusNT", obj.getStatusNT());
        long row = db.insert("tb_napTien", null, values);
        return row>0;
    }
    public boolean UpdateSttNT(NapTien obj) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("statusNT", obj.getStatusNT());
        long row = db.update("tb_napTien", values, "idNT=?",new String[]{String.valueOf(obj.getIdNT())});
        return (row > 0);
    }
}
