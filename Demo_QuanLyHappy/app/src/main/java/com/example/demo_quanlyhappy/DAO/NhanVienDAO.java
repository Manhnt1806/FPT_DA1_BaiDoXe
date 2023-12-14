package com.example.demo_quanlyhappy.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.DataBase.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public NhanVienDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<NhanVien> getAll(String...agrs){
        db = dbHelper.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT * FROM tb_nhanVien ORDER BY idNV ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _idNV = cs.getInt(0);
            String _cccdNV = cs.getString(1);
            String _chucVu = cs.getString(2);
            String _sdtNV = cs.getString(3);
            String _passNV = cs.getString(4);
            String _tenNV = cs.getString(5);
            int _statusNV = cs.getInt(6);
            NhanVien nv = new NhanVien(_idNV, _cccdNV, _chucVu, _sdtNV, _passNV, _tenNV, _statusNV);
            list.add(nv);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public NhanVien getID(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_nhanVien WHERE sdtNV=?";
        List<NhanVien> list = getData(sql, id);
        return list.get(0);
    }
    public ArrayList<NhanVien> getChucVu(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_nhanVien WHERE chucVu=?";
        List<NhanVien> list = getData(sql, id);
        return (ArrayList<NhanVien>) list;
    }
    public NhanVien getID2(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_nhanVien WHERE idNV=?";
        List<NhanVien> list = getData(sql, id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<NhanVien> getData(String sql, String...SelectArgs){
        db = dbHelper.getWritableDatabase();
        List<NhanVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            NhanVien object = new NhanVien();
            object.setIdNV(Integer.parseInt(c.getString(c.getColumnIndex("idNV"))));
            object.setCccdNV(c.getString(c.getColumnIndex("cccdNV")));
            object.setChucVu(c.getString(c.getColumnIndex("chucVu")));
            object.setSdtNV(c.getString(c.getColumnIndex("sdtNV")));
            object.setPassNV(c.getString(c.getColumnIndex("passNV")));
            object.setTenNV(c.getString(c.getColumnIndex("tenNV")));
            object.setStatusNV(Integer.parseInt(c.getString(c.getColumnIndex("statusNV"))));
            list.add(object);
        }
        return list;
    }
    public int checkLogin(String username, String password){
        db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM tb_nhanVien WHERE sdtNV = ? AND passNV = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
        if(cursor.getCount()==0){
            return -1;
        }
        return 1;
    }
    public boolean Insert(NhanVien obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cccdNV", obj.getCccdNV());
        values.put("chucVu", obj.getChucVu());
        values.put("sdtNV", obj.getSdtNV());
        values.put("passNV", obj.getPassNV());
        values.put("tenNV", obj.getTenNV());
        values.put("statusNV", obj.getStatusNV());
        long row = db.insert("tb_nhanVien", null, values);
        return row>0;
    }
    public boolean Update(NhanVien obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idNV", obj.getIdNV());
        values.put("cccdNV", obj.getCccdNV());
        values.put("chucVu", obj.getChucVu());
        values.put("sdtNV", obj.getSdtNV());
        values.put("passNV", obj.getPassNV());
        values.put("tenNV", obj.getTenNV());
        values.put("statusNV", obj.getStatusNV());
        String [] tham_so = new String[]{obj.getIdNV()+ ""};
        int row = db.update("tb_nhanVien", values, "idNV=?", tham_so);
        return (row>0);
    }
}
