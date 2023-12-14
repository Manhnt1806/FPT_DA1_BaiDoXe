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

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public KhachHangDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<KhachHang> getAll(String...agrs){
        db = dbHelper.getReadableDatabase();
        ArrayList<KhachHang> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM tb_khachHang ORDER BY idKH ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _idKH = cs.getInt(0);
            String _cccdKH = cs.getString(1);
            String _sdtKH = cs.getString(2);
            String _passKH = cs.getString(3);
            String _tenKH = cs.getString(4);
            int _soDuKH = cs.getInt(5);
            int _goiSD= cs.getInt(6);
            KhachHang kh = new KhachHang(_idKH, _cccdKH, _sdtKH, _passKH, _tenKH, _soDuKH, _goiSD);
            list.add(kh);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public KhachHang getID(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_khachHang WHERE sdtKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }
    public KhachHang getID2(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_khachHang WHERE idKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }
    public ArrayList<KhachHang> getDKve(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_khachHang WHERE goiSD=?";
        List<KhachHang> list = getData(sql, id);
        return (ArrayList<KhachHang>) list;
    }
    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String...SelectArgs){
        db = dbHelper.getWritableDatabase();
        List<KhachHang> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            KhachHang object = new KhachHang();
            object.setIdKH(Integer.parseInt(c.getString(c.getColumnIndex("idKH"))));
            object.setCccdKH(c.getString(c.getColumnIndex("cccdKH")));
            object.setSdtKH(c.getString(c.getColumnIndex("sdtKH")));
            object.setPassKH(c.getString(c.getColumnIndex("passKH")));
            object.setTenKH(c.getString(c.getColumnIndex("tenKH")));
            object.setSoDuKH(Integer.parseInt(c.getString(c.getColumnIndex("soDuKH"))));
            object.setGoiSD(Integer.parseInt(c.getString(c.getColumnIndex("goiSD"))));
            list.add(object);
        }
        return list;
    }
    public int checkLogin(String username, String password){
        db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM tb_khachHang WHERE sdtKH = ? AND passKH = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
        if(cursor.getCount()==0){
            return -1;
        }
        return 1;
    }
    public boolean Insert(KhachHang obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cccdKH", obj.getCccdKH());
        values.put("sdtKH", obj.getSdtKH());
        values.put("passKH", obj.getPassKH());
        values.put("tenKH", obj.getTenKH());
        values.put("soDuKH", obj.getSoDuKH());
        values.put("goiSD", obj.getGoiSD());
        long row = db.insert("tb_khachHang", null, values);
        return row>0;
    }
    public boolean Update(KhachHang obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idKH", obj.getIdKH());
        values.put("cccdKH", obj.getCccdKH());
        values.put("sdtKH", obj.getSdtKH());
        values.put("passKH", obj.getPassKH());
        values.put("tenKH", obj.getTenKH());
        values.put("soDuKH", obj.getSoDuKH());
        values.put("goiSD", obj.getGoiSD());
        String [] tham_so = new String[]{obj.getIdKH()+ ""};
        int row = db.update("tb_khachHang", values, "idKH=?", tham_so);
        return (row>0);
    }
    public boolean UpdateSoDu(KhachHang obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soDuKH", obj.getSoDuKH());
        String [] tham_so = new String[]{obj.getIdKH()+ ""};
        int row = db.update("tb_khachHang", values, "idKH=?", tham_so);
        return (row>0);
    }
}
