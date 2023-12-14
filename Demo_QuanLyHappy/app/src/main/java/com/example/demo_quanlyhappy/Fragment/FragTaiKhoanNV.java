package com.example.demo_quanlyhappy.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.MainChinh;
import com.example.demo_quanlyhappy.MainLogin;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class FragTaiKhoanNV extends Fragment {
    CardView cvTaiKhoanNV, cvQuanLyTK, cvDoiSdtNV, cvDoiMkNV, cvLogOutNV, cvThongKeQL;
    NhanVienDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_taikhoan_nv, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NhanVien nv = (NhanVien) getArguments().get("quyen5");
        cvTaiKhoanNV = view.findViewById(R.id.cv_taiKhoanNV);
        cvQuanLyTK = view.findViewById(R.id.cv_quanLyTK);
        cvDoiSdtNV = view.findViewById(R.id.cv_doiSdtNV);
        cvThongKeQL = view.findViewById(R.id.cv_thongKeQL);
        cvDoiMkNV = view.findViewById(R.id.cv_doiMkNV);
        cvLogOutNV = view.findViewById(R.id.cv_logoutNV);
        dao = new NhanVienDAO(getActivity());
        cvTaiKhoanNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_taikhoannv, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                TextView tvTenNV = view.findViewById(R.id.tv_ten_NV);
                TextView tvCccdNV = view.findViewById(R.id.tv_cccd_NV);
                TextView tvSdtNV = view.findViewById(R.id.tv_sdt_NV);
                TextView tvGoiNV = view.findViewById(R.id.tv_chucVu);
                ImageView btnBack = view.findViewById(R.id.btn_back_tkNV);
                tvTenNV.setText(nv.getTenNV());
                tvCccdNV.setText(nv.getCccdNV());
                tvSdtNV.setText(nv.getSdtNV());
                tvGoiNV.setText(nv.getChucVu());
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
            }
        });
        cvQuanLyTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nv.getChucVu().equals("Quản lý")){
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragQuanLyNV fragQuanLyNV = new FragQuanLyNV();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame,fragQuanLyNV)
                            .commit();
//                    toolbar.setTitle("Thống kê");
                }else {
                    Toast.makeText(getContext(), "Không có quyền truy cập", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cvDoiSdtNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                inputSdtMoiKH.getEditText().setText(nv.getSdtNV());
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sdt = nv.getSdtNV();
                        String pass = nv.getPassNV();
                        if (!pass.equals(inputPass.getEditText().getText().toString())) {
                            tilpass.setError("Mật khẩu chưa đúng!");
                        } else {
                            NhanVien nv = dao.getID(sdt);
                            nv.setSdtNV(inputSdtMoiKH.getEditText().getText().toString());
                            if (dao.Update(nv)) {
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
        cvDoiMkNV.setOnClickListener(new View.OnClickListener() {
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
                        String sdt = nv.getSdtNV();
                        String PassCu = inputPassCu.getEditText().getText().toString();
                        String passMoi = inputPassMoi.getEditText().getText().toString();
                        String passMoi2 = inputRePass.getEditText().getText().toString();
                        if(PassCu.length()==0 || passMoi.length()==0 || passMoi2.length()==0){
                            Toast.makeText(getContext(), "Vui lòng nhập nhập đủ!", Toast.LENGTH_SHORT).show();
                        }else{
                            if (!PassCu.equals(nv.getPassNV())) {
                                Toast.makeText(getContext(), "Mật khẩu cũ chưa đúng!", Toast.LENGTH_SHORT).show();
                            }else if(!passMoi.equals(passMoi2)){
                                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                            }else if(PassCu.equals(passMoi)){
                                Toast.makeText(getContext(), "Mật khẩu mới trùng khớp mật khẩu cũ", Toast.LENGTH_SHORT).show();
                            } else {
                                NhanVien nv = dao.getID(sdt);
                                nv.setPassNV(passMoi);
                                if (dao.Update(nv)) {
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
        cvThongKeQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nv.getChucVu().equals("Quản lý")){
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragThongKeNV fragThongKeNV = new FragThongKeNV();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("quyen6", nv);
                    fragThongKeNV.setArguments(bundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame,fragThongKeNV)
                            .commit();
//                    toolbar.setTitle("Thống kê");
                }else {
                    Toast.makeText(getContext(), "Không có quyền truy cập", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cvLogOutNV.setOnClickListener(new View.OnClickListener() {
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
