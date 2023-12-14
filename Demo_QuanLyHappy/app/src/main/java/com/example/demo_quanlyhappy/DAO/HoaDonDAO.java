package com.example.demo_quanlyhappy.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DataBase.DbHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public HoaDonDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public ArrayList<HoaDon> getAll(String...agrs){
        db = dbHelper.getReadableDatabase();
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cs = db.rawQuery("SELECT k.idHD, k.idXe, k.htTT, k.tienTT, k.idKH, k.tgVao, k.tgRa, k.haVao, k.haRa, k.statusHD, l.statusGuiXe FROM tb_hoaDon k, tb_xe l WHERE k.idXe = l.idXe ORDER BY idHD ASC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int _idHD = cs.getInt(0);
            int _idGuiXe = cs.getInt(1);
            int _htTT = cs.getInt(2);
            int _tienTT = cs.getInt(3);
            int _idKH = cs.getInt(4);
            Date _tgVao = new Date(System.currentTimeMillis());
            try {
                _tgVao = sdf.parse(cs.getString(5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date _tgRa = new Date(System.currentTimeMillis());
            try {
                _tgRa = sdf.parse(cs.getString(6));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            byte[] _haVao = cs.getBlob(7);
            byte[] _haRa = cs.getBlob(8);
            int _statusHD = cs.getInt(9);
            int _statusGuiXe = cs.getInt(10);
            HoaDon hd = new HoaDon(_idHD, _idGuiXe, _htTT, _tienTT, _idKH, _tgVao, _tgRa, _haVao, _haRa, _statusHD, _statusGuiXe);
            list.add(hd);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return list;
    }
    public HoaDon getID(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_hoaDon WHERE idXe=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }
    public ArrayList<HoaDon> getIDKH(String id){
        db = dbHelper.getWritableDatabase();
        String sql="SELECT * FROM tb_hoaDon WHERE idKH=?";
        List<HoaDon> list = getData(sql, id);
        return (ArrayList<HoaDon>) list;
    }
    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String...SelectArgs){
        db = dbHelper.getWritableDatabase();
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            HoaDon object = new HoaDon();
            object.setIdHD(Integer.parseInt(c.getString(c.getColumnIndex("idHD"))));
            object.setIdGuiXe(Integer.parseInt(c.getString(c.getColumnIndex("idXe"))));
            object.setHtTT(Integer.parseInt(c.getString(c.getColumnIndex("htTT"))));
            object.setTienTT(Integer.parseInt(c.getString(c.getColumnIndex("tienTT"))));
            object.setIdKH(Integer.parseInt(c.getString(c.getColumnIndex("idKH"))));
            try {
                object.setTgVao(sdf.parse(c.getString(c.getColumnIndex("tgVao"))));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            try {
                object.setTgRa(sdf.parse(c.getString(c.getColumnIndex("tgRa"))));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            object.setHaVao(c.getBlob(c.getColumnIndex("haVao")));
            object.setHaRa(c.getBlob(c.getColumnIndex("haRa")));
            object.setStatusHD(Integer.parseInt(c.getString(c.getColumnIndex("statusHD"))));
            list.add(object);
        }
        return list;
    }
    public boolean Insert(HoaDon obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idXe", obj.getIdGuiXe());
        values.put("htTT", obj.getHtTT());
        values.put("tienTT", obj.getTienTT());
        values.put("idKH", obj.getIdKH());
        values.put("tgVao", sdf.format(obj.getTgVao()));
        values.put("tgRa", sdf.format(obj.getTgRa()));
        values.put("haVao", obj.getHaVao());
        values.put("haRa", obj.getHaRa());
        values.put("statusHD", obj.getStatusHD());
        long row = db.insert("tb_hoaDon", null, values);
        return row>0;
    }
    public boolean Update(HoaDon obj){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idXe", obj.getIdGuiXe());
        values.put("htTT", obj.getHtTT());
        values.put("tienTT", obj.getTienTT());
        values.put("idKH", obj.getIdKH());
        values.put("tgVao", sdf.format(obj.getTgVao()));
        values.put("tgRa", sdf.format(obj.getTgRa()));
        values.put("haVao", obj.getHaVao());
        values.put("haRa", obj.getHaRa());
        values.put("statusHD", obj.getStatusHD());
        String [] tham_so = new String[]{obj.getIdHD()+ ""};
        int row = db.update("tb_hoaDon", values, "idHD=?", tham_so);
        return (row>0);
    }
    public int DoanhThu(String tuNgay, String denNgay){
        db = dbHelper.getWritableDatabase();
        int dt = 0;
        String sqlDT = "SELECT SUM(tienTT) as doanhThu FROM tb_hoaDon WHERE tgRa BETWEEN ? AND ?";
        Cursor cs = db.rawQuery(sqlDT, new String[]{tuNgay, denNgay});
        if (cs.getCount() != 0) {
            cs.moveToFirst();
            dt = cs.getInt(0);
        }
        return dt;
    }
}
