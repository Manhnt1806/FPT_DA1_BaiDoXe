package com.example.demo_quanlyhappy.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.demo_quanlyhappy.DAO.HoaDonVeDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.HoaDonVe;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.MainLogin;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragTaiKhoanKH extends Fragment {
    CardView cvTaiKhoanKH, cvDangKyGoi, cvDoiSdtKH, cvDoiMkKH, cvHoTroKH, cvLogOutKH;
    KhachHangDAO dao;
    HoaDonVeDAO daoHDV;
    XeDAO daoXe;
    ArrayList<Xe> listXe = new ArrayList<>();
    ArrayList<HoaDonVe> list = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_taikhoan_kh, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KhachHang kh1 = (KhachHang) getArguments().get("quyen4");
        cvTaiKhoanKH = view.findViewById(R.id.cv_taiKhoanKH);
        cvDangKyGoi = view.findViewById(R.id.cv_dangKyVeGui);
        cvDoiSdtKH = view.findViewById(R.id.cv_sdtKH);
        cvDoiMkKH = view.findViewById(R.id.cv_doiMkKH);
        cvHoTroKH = view.findViewById(R.id.cv_hoTroKH);
        cvLogOutKH = view.findViewById(R.id.cv_LogOutKH);
        dao = new KhachHangDAO(getActivity());
        daoHDV = new HoaDonVeDAO(getContext());
        daoXe = new XeDAO(getContext());
        cvTaiKhoanKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHang kh = dao.getID2(String.valueOf(kh1.getIdKH()));
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_taikhoan, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextView tvTenKH = view.findViewById(R.id.tv_ten_KH);
                TextView tvCccdKH = view.findViewById(R.id.tv_cccd_KH);
                TextView tvSdtKH = view.findViewById(R.id.tv_sdt_KH);
                TextView tvGoiDK = view.findViewById(R.id.tv_goiDK);
                ImageView btnBack = view.findViewById(R.id.btn_back_tkKH);
                tvTenKH.setText(kh.getTenKH());
                tvCccdKH.setText(kh.getCccdKH());
                tvSdtKH.setText(kh.getSdtKH());
                if (kh.getGoiSD() == 1) {
                    tvGoiDK.setText("Gói tháng");
                }else if (kh.getGoiSD() == 2) {
                    tvGoiDK.setText("Gói năm");
                }else{
                    tvGoiDK.setText("Gói ngày");
                }

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
        cvDangKyGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_dangkygoi, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                TextInputLayout inputPass = view.findViewById(R.id.intput_pass_dkg);
                TextView tvTenKH = view.findViewById(R.id.tv_ten_KH);
                RadioButton rdoVeThang = view.findViewById(R.id.rdo_veThang);
                RadioButton rdoVeNam = view.findViewById(R.id.rdo_veNam);
                Button btnDone = view.findViewById(R.id.btn_dangKyGoi);
                Button btnCancel = view.findViewById(R.id.btn_huyDangKy);
                tvTenKH.setText(kh1.getTenKH());
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tienTT = 0;
                        KhachHang kh = dao.getID2(String.valueOf(kh1.getIdKH()));
                        listXe = daoXe.getDangSd1(String.valueOf(kh.getIdKH()), String.valueOf(0));
                        String pass = inputPass.getEditText().getText().toString();
                        Date objDate = new Date(System.currentTimeMillis());
                        DateFormat dateFormat = new DateFormat();
                        String chuoi_ngay_thang_nam = (String) dateFormat.format("yyyy/MM/dd", objDate);
                        if(pass.equals(kh.getPassKH()) || !pass.isEmpty()){
                            if(kh.getGoiSD()==1 && rdoVeThang.isChecked()){
                                Toast.makeText(getContext(), "Bạn đã đăng ký vé tháng", Toast.LENGTH_SHORT).show();
                            }else if(kh.getGoiSD()==2 && rdoVeNam.isChecked()){
                                Toast.makeText(getContext(), "Bạn đã đăng ký vé năm", Toast.LENGTH_SHORT).show();
                            }else if(kh.getGoiSD()==2 && rdoVeThang.isChecked()){
                                Toast.makeText(getContext(), "Bạn đã đăng ký vé năm", Toast.LENGTH_SHORT).show();
                            } else if (listXe.size()==0) {
                                Toast.makeText(getContext(), "Bạn chưa đăng ký xe", Toast.LENGTH_SHORT).show();
                            } else {
                                if(rdoVeThang.isChecked()){
                                    if (listXe.size()==1){
                                        tienTT = 120000;
                                    }else if(listXe.size()==2){
                                        tienTT = 105000;
                                    }else if(listXe.size()>=3){
                                        tienTT = 90000;
                                    }
                                    if(kh.getSoDuKH() - tienTT<0){
                                        Toast.makeText(getContext(), "Số dư của bạ không đủ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        kh.setGoiSD(1);
                                        dao.Update(kh);
                                        int idKH = kh.getIdKH();
                                        int tienTTve = tienTT;
                                        Date tgTTve = null;
                                        try {
                                            tgTTve = sdf.parse(chuoi_ngay_thang_nam);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        int goiSD = 1;
                                        HoaDonVe hdV = new HoaDonVe(idKH, tienTTve, tgTTve, goiSD);
                                        if(daoHDV.Insert(hdV)){
                                            Toast.makeText(getContext(), "Đăng ký vé tháng thành công", Toast.LENGTH_SHORT).show();
                                            list.clear();
                                            list.addAll(daoHDV.getIDKH(String.valueOf(idKH)));
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(getContext(), "Đăng ký vé tháng thành công thất bại", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }else {
                                    if (listXe.size()==1){
                                        tienTT = 1050000;
                                    }else if(listXe.size()==2){
                                        tienTT = 900000;
                                    }else if(listXe.size()>=3){
                                        tienTT = 750000;
                                    }
                                    if(kh.getSoDuKH() - tienTT<0){
                                        Toast.makeText(getContext(), "Số dư của bạ không đủ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        kh.setGoiSD(2);
                                        dao.Update(kh);
                                        int idKH = kh.getIdKH();
                                        int tienTTve = tienTT;
                                        Date tgTTve = null;
                                        try {
                                            tgTTve = sdf.parse(chuoi_ngay_thang_nam);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        int goiSD = 1;
                                        HoaDonVe hdV = new HoaDonVe(idKH, tienTTve, tgTTve, goiSD);
                                        if(daoHDV.Insert(hdV)){
                                            Toast.makeText(getContext(), "Đăng ký vé năm thành công", Toast.LENGTH_SHORT).show();
                                            list.clear();
                                            list.addAll(daoHDV.getIDKH(String.valueOf(idKH)));
                                            dialog.dismiss();
                                        }else{
                                            Toast.makeText(getContext(), "Đăng ký vé năm thành công thất bại", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(getContext(), "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        cvDoiSdtKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KhachHang kh = dao.getID2(String.valueOf(kh1.getIdKH()));
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_dosdtkh, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextInputLayout inputPass = view.findViewById(R.id.intput_pass_doisdt);
                TextInputLayout inputSdtMoiKH = view.findViewById(R.id.intput_sdtmoi_KH);
                TextInputLayout tilpass = view.findViewById(R.id.intput_pass_doisdt);
                Button btnSave = view.findViewById(R.id.btn_save_doisdt_KH);
                Button btnHuy = view.findViewById(R.id.btn_exit_doisdt_KH);
                inputSdtMoiKH.getEditText().setText(kh.getSdtKH());
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sdt = kh.getSdtKH();
                        String pass = kh.getPassKH();
                        if (!pass.equals(inputPass.getEditText().getText().toString())) {
                            tilpass.setError("Mật khẩu chưa đúng!");
                        } else {
                            KhachHang kh = dao.getID(sdt);
                            kh.setSdtKH(inputSdtMoiKH.getEditText().getText().toString());
                            if (dao.Update(kh)) {
                                Toast.makeText(getContext(), "Thay đổi hoàn tất!", Toast.LENGTH_SHORT).show();
                                inputPass.getEditText().setText("");
                                inputSdtMoiKH.getEditText().setText("");
                            } else {
                                Toast.makeText(getContext(), "Thay đổi thất bại!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });
        cvDoiMkKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_doipasskh, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextInputLayout inputPassCu = view.findViewById(R.id.intput_pass_cu_KH);
                TextInputLayout inputPassMoi = view.findViewById(R.id.intput_pass_moi_KH);
                TextInputLayout inputRePass = view.findViewById(R.id.intput_repass_moi_KH);
                Button btnSave = view.findViewById(R.id.btn_save_doisdt_KH);
                Button btnHuy = view.findViewById(R.id.btn_exit_doisdt_KH);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sdt = kh1.getSdtKH();
                        String PassCu = inputPassCu.getEditText().getText().toString();
                        String passMoi = inputPassMoi.getEditText().getText().toString();
                        String passMoi2 = inputRePass.getEditText().getText().toString();
                        if(PassCu.length()==0 || passMoi.length()==0 || passMoi2.length()==0){
                            Toast.makeText(getContext(), "Vui lòng nhập nhập đủ!", Toast.LENGTH_SHORT).show();
                        }else{
                            if (!PassCu.equals(kh1.getPassKH())) {
                                Toast.makeText(getContext(), "Mật khẩu cũ chưa đúng!", Toast.LENGTH_SHORT).show();
                            }else if(!passMoi.equals(passMoi2)){
                                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                            }else if(PassCu.equals(passMoi)){
                                Toast.makeText(getContext(), "Mật khẩu mới trùng khớp mật khẩu cũ", Toast.LENGTH_SHORT).show();
                            } else {
                                KhachHang kh = dao.getID(sdt);
                                kh.setPassKH(passMoi);
                                if (dao.Update(kh)) {
                                    Toast.makeText(getContext(), "Thay đổi hoàn tất!", Toast.LENGTH_SHORT).show();
                                    inputPassCu.getEditText().setText("");
                                    inputPassMoi.getEditText().setText("");
                                    inputRePass.getEditText().setText("");
                                } else {
                                    Toast.makeText(getContext(), "Thay đổi thất bại!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                });
            }
        });
        cvHoTroKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_hotro_kh, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
        cvLogOutKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mDialog = new AlertDialog.Builder(getContext());
                mDialog.setTitle("Question");
                mDialog.setMessage("Are you sure You want to Log out?");
                mDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), MainLogin.class);
                        startActivity(intent);
                    }
                });
                mDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                mDialog.create().show();
            }
        });
    }
}
