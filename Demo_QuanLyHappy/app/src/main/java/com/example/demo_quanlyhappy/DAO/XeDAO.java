package com.example.demo_quanlyhappy.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.DataBase.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XeDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public XeDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<Xe> getAll(String...agrs){
        db = dbHelper.getReadableDatabase();
        ArrayList<Xe> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM tb_xe ORDER BY idXe ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _idXe = cs.getInt(0);
            int _idKH = cs.getInt(1);
            String _tenXe = cs.getString(2);
            String _bienSo = cs.getString(3);
            byte[] _dangKy = cs.getBlob(4);
            int _statusXe = cs.getInt(5);
            int _idNV = cs.getInt(6);
            int _statusGX = cs.getInt(7);
            Xe nt = new Xe(_idXe, _idKH, _tenXe, _bienSo, _dangKy, _statusXe, _idNV, _statusGX);
            list.add(nt);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public ArrayList<Xe> getDangSd(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE idKH=? ";
        List<Xe> list = getData(sql, id);
        return (ArrayList<Xe>) list;
    }
    public ArrayList<Xe> getDangSd1(String id, String stt){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE idKH=? AND statusXe=?";
        List<Xe> list = getData(sql, id, stt);
        return (ArrayList<Xe>) list;
    }
    public ArrayList<Xe> getDangSd2(String stt){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE statusXe=?";
        List<Xe> list = getData(sql, stt);
        return (ArrayList<Xe>) list;
    }
    public ArrayList<Xe> getDangGui(String id, String stt){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE idKH=? AND statusGuiXe=?";
        List<Xe> list = getData(sql, id, stt);
        return (ArrayList<Xe>) list;
    }
    public ArrayList<Xe> getTrong(String stt){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE statusGuiXe=?";
        List<Xe> list = getData(sql, stt);
        return (ArrayList<Xe>) list;
    }
    public Xe getBienSo(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE bienSoXe=?";
        List<Xe> list = getData(sql, id);
        return list.get(0);
    }
    public Xe getID(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_xe WHERE idKH=?";
        List<Xe> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<Xe> getData(String sql, String...SelectArgs){
        db = dbHelper.getWritableDatabase();
        List<Xe> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            Xe object = new Xe();
            object.setIdXe(Integer.parseInt(c.getString(c.getColumnIndex("idXe"))));
            object.setIdKH(Integer.parseInt(c.getString(c.getColumnIndex("idKH"))));
            object.setTenXe(c.getString(c.getColumnIndex("tenXe")));
            object.setBienSoXe(c.getString(c.getColumnIndex("bienSoXe")));
            object.setDangKyXe(c.getBlob(c.getColumnIndex("dangKyXe")));
            object.setStatusXe(Integer.parseInt(c.getString(c.getColumnIndex("statusXe"))));
            object.setIdNV(Integer.parseInt(c.getString(c.getColumnIndex("idNV"))));
            object.setStatusGuiXe(Integer.parseInt(c.getString(c.getColumnIndex("statusGuiXe"))));
            list.add(object);
        }
        return list;
    }
    public boolean Insert(Xe obj) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKH", obj.getIdKH());
        values.put("tenXe", obj.getTenXe());
        values.put("bienSoXe", obj.getBienSoXe());
        values.put("dangKyXe", obj.getDangKyXe());
        values.put("statusXe", obj.getStatusXe());
        values.put("idNV", obj.getIdNV());
        values.put("statusGuiXe", obj.getStatusGuiXe());
        long row = db.insert("tb_xe", null, values);
        return (row > 0);
    }
    public boolean UpdateSttGX(Xe obj) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("statusXe", obj.getStatusXe());
        values.put("statusGuiXe", obj.getStatusGuiXe());
        long row = db.update("tb_xe", values, "idXe=?",new String[]{String.valueOf(obj.getIdXe())});
        return (row > 0);
    }
}
