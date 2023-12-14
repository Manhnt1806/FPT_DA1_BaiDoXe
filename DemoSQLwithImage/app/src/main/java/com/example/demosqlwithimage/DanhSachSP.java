package com.example.demosqlwithimage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhSachSP extends AppCompatActivity {

    ArrayList<SanPham> arraySanPham;
    ListView lvDanhsach;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sp);

        lvDanhsach = (ListView) findViewById(R.id.lvDanhsach);

        Cursor cursor = db.TruyVanTraVe("Select * from SanPham");
        arraySanPham = new ArrayList<>();
        while (cursor.moveToNext()) {
            arraySanPham.add(new SanPham(cursor.getString(1), cursor.getInt(2), cursor.getBlob(3)));
        }
        CusAdapter adapter = new CusAdapter(DanhSachSP.this, R.layout.row_listiew, arraySanPham);
        lvDanhsach.setAdapter(adapter);
    }
}