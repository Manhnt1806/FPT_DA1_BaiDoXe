package com.example.demo_quanlyhappy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.Fragment.FragCheckNT;
import com.example.demo_quanlyhappy.Fragment.FragQuanLyXeKH;
import com.example.demo_quanlyhappy.Fragment.FragQuanLyXeNV;
import com.example.demo_quanlyhappy.Fragment.FragQuetKH;
import com.example.demo_quanlyhappy.Fragment.FragQuetNV;
import com.example.demo_quanlyhappy.Fragment.FragQuetQR;
import com.example.demo_quanlyhappy.Fragment.FragTaiKhoanKH;
import com.example.demo_quanlyhappy.Fragment.FragTaiKhoanNV;
import com.example.demo_quanlyhappy.Fragment.FragThongKeKH;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainChinh extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chinh);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        replaceFragment(new FragQuetQR());
        navView.getMenu().findItem(R.id.nav_QR).setChecked(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Quét biển số");
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            NhanVien nhanVien = (NhanVien) getIntent().getExtras().get("quyen");
            KhachHang khachHang = (KhachHang) getIntent().getExtras().get("quyen1");
            Bundle bundle = new Bundle();
            FragmentManager fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.nav_QR:
                    if(nhanVien!=null){
                        FragQuetNV frag_quetQR = new FragQuetNV();
                        bundle.putSerializable("quyen123", nhanVien);
                        frag_quetQR.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame, frag_quetQR)
                                .commit();
                        toolbar.setTitle("Quét biển số");
                    }else {
                        FragQuetKH frag_quetQR = new FragQuetKH();
                        bundle.putSerializable("quyen124", khachHang);
                        frag_quetQR.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame, frag_quetQR)
                                .commit();
                        toolbar.setTitle("Quét biển số");
                    }
                    break;
                case R.id.nav_QLGX:
                    if(nhanVien!=null){
                        FragQuanLyXeNV fragQuanLyXeNV = new FragQuanLyXeNV();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,fragQuanLyXeNV)
                                .commit();
                        toolbar.setTitle("Thẻ xe");
                    }else{
                        FragQuanLyXeKH fragQuanLyXeKH = new FragQuanLyXeKH();
                        bundle.putSerializable("quyen3", khachHang);
                        fragQuanLyXeKH.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,fragQuanLyXeKH)
                                .commit();
                        toolbar.setTitle("Thẻ xe");
                    }
                    break;
                case R.id.nav_Thongke:
                    if(nhanVien!=null){
                        FragCheckNT fragCheckNT = new FragCheckNT();
                        bundle.putSerializable("quyen6", nhanVien);
                        fragCheckNT.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,fragCheckNT)
                                .commit();
                        toolbar.setTitle("Xác nhận nạp tiền");
                    }else {
                        FragThongKeKH frag_thongKeKH = new FragThongKeKH();
                        bundle.putSerializable("quyen2", khachHang);
                        frag_thongKeKH.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame, frag_thongKeKH)
                                .commit();
                        toolbar.setTitle("Thống kê");
                    }
                    break;
                case R.id.nav_Taikhoan:
                    if(nhanVien!=null){
                        FragTaiKhoanNV fragTaiKhoanNV = new FragTaiKhoanNV();
                        bundle.putSerializable("quyen5", nhanVien);
                        fragTaiKhoanNV.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,fragTaiKhoanNV)
                                .commit();
                        toolbar.setTitle("Tài khoản");
                    }else{
                        FragTaiKhoanKH fragTaiKhoanKH = new FragTaiKhoanKH();
                        bundle.putSerializable("quyen4", khachHang);
                        fragTaiKhoanKH.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame,fragTaiKhoanKH)
                                .commit();
                        toolbar.setTitle("Tài khoản");
                    }
                    break;
            }
            return false;
        }
    };
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

}