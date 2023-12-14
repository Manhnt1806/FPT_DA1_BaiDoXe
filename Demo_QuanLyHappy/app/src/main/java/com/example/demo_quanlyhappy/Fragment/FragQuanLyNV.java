package com.example.demo_quanlyhappy.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.Adapter.CheckNapTienAdapter;
import com.example.demo_quanlyhappy.Adapter.NhanVienAdapter;
import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.MainLogin;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragQuanLyNV extends Fragment {
    RecyclerView rcv;
    NhanVienDAO dao;
    ArrayList<NhanVien> list =new ArrayList<>();
    NhanVienAdapter adapter;
    Button btnAddNV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlynv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_quanLyNV);
        btnAddNV = view.findViewById(R.id.btn_addTaiKhoanNV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new NhanVienDAO(getContext());
        list = dao.getChucVu("Nhân viên");
        adapter = new NhanVienAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        btnAddNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_dk_nv, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextInputLayout inputTenNV = view.findViewById(R.id.intput_dk_tenNV);
                TextInputLayout inputCccdNV = view.findViewById(R.id.intput_dk_cccdNV);
                TextInputLayout inputSdtNV = view.findViewById(R.id.intput_dk_sdtNV);
                TextInputLayout inputPassNV = view.findViewById(R.id.intput_dk_passNV);
                TextInputLayout inputRePassNV = view.findViewById(R.id.intput_dk_repassNV);
                Button btnSave = view.findViewById(R.id.btn_save_dk_NV);
                Button btnHuy = view.findViewById(R.id.btn_exit_dk_NV);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String hoTen = inputTenNV.getEditText().getText().toString();
                        String Cccd = inputCccdNV.getEditText().getText().toString();
                        String Sdt = inputSdtNV.getEditText().getText().toString();
                        String Pass = inputPassNV.getEditText().getText().toString();
                        String RePass = inputRePassNV.getEditText().getText().toString();
                        String chucVu = "Nhân viên";
                        int sttNV = 1;
                        NhanVien nv = new NhanVien(Cccd,chucVu, Sdt,Pass, hoTen, sttNV);
                        if(hoTen.isEmpty()||Cccd.isEmpty()||Sdt.isEmpty()||Pass.isEmpty()||RePass.isEmpty()){
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else if(!Pass.equals(RePass)){
                            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        }else {
                            if (dao.Insert(nv)) {
                                Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(dao.getAll());
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }else{
                                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
