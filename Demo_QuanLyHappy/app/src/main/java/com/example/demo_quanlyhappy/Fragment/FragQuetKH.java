package com.example.demo_quanlyhappy.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragQuetKH extends Fragment {
    ImageView imgQR;
    Button btnQuet;
    XeDAO daoXe;
    HoaDonDAO daoHD;
    KhachHangDAO daoKH;
    NhanVienDAO daoNV;
    ArrayList<HoaDon> list =new ArrayList<>();
    ArrayList<Xe> listXe =new ArrayList<>();
    ImageView imgXacThuc;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quet_qr, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KhachHang kh = (KhachHang) getArguments().get("quyen124");
        imgQR = view.findViewById(R.id.imgQR);
        btnQuet = view.findViewById(R.id.btn_quet);
        btnQuet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanOptions intentIntegrator = new ScanOptions();
                intentIntegrator.setPrompt("QRCode");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(CaptureAct.class);
                barLauncher.launch(intentIntegrator);
            }
        });
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null){
            KhachHang kh = (KhachHang) getArguments().get("quyen124");
            daoNV = new NhanVienDAO(getContext());
            daoXe = new XeDAO(getContext());
            daoHD = new HoaDonDAO(getContext());
            daoKH = new KhachHangDAO(getContext());
            listXe = daoXe.getAll();
            Xe xe = daoXe.getBienSo(result.getContents());
            if(xe.getIdKH()==kh.getIdKH()){
                list = daoHD.getAll();
                HoaDon hd = list.get(list.size() - 1);
                if(kh.getGoiSD()==1 || kh.getGoiSD()==2){
                    hd.setTienTT(0);
                    hd.setStatusHD(1);
                    xe.setStatusGuiXe(1);
                    if (daoXe.UpdateSttGX(xe)) {
                        Toast.makeText(getContext(), "Xe đang vắng", Toast.LENGTH_SHORT).show();
                        listXe.clear();
                        listXe.addAll(daoXe.getAll());
                    } else {
                        Toast.makeText(getContext(), "Xe đang gửi", Toast.LENGTH_SHORT).show();
                    }
                    Date objDate = new Date(System.currentTimeMillis());
                    DateFormat dateFormat = new DateFormat();
                    String chuoi_ngay_thang_nam = (String) dateFormat.format("yyyy/MM/dd", objDate);
                    try {
                        hd.setTgRa(simpleDateFormat.parse(chuoi_ngay_thang_nam));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (daoHD.Update(hd)) {
                        Toast.makeText(getContext(), "Thanh toán hóa đơn thành công", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(daoHD.getAll());
                    } else {
                        Toast.makeText(getContext(), "Không thể thanh toán hóa đơn", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if(kh.getSoDuKH()<5000){
                        Toast.makeText(getContext(), "Số dư không đủ", Toast.LENGTH_SHORT).show();
                    }else{
                        hd.setTienTT(5000);
                        hd.setStatusHD(1);
                        xe.setStatusGuiXe(1);
                        if (daoXe.UpdateSttGX(xe)) {
                            Toast.makeText(getContext(), "Xe đang vắng", Toast.LENGTH_SHORT).show();
                            listXe.clear();
                            listXe.addAll(daoXe.getAll());
                        } else {
                            Toast.makeText(getContext(), "Xe đang gửi", Toast.LENGTH_SHORT).show();
                        }
                        Date objDate = new Date(System.currentTimeMillis());
                        DateFormat dateFormat = new DateFormat();
                        String chuoi_ngay_thang_nam = (String) dateFormat.format("yyyy/MM/dd", objDate);
                        try {
                            hd.setTgRa(simpleDateFormat.parse(chuoi_ngay_thang_nam));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (daoHD.Update(hd)) {
                            Toast.makeText(getContext(), "Thanh toán hóa đơn thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(daoHD.getAll());
                        } else {
                            Toast.makeText(getContext(), "Không thể thanh toán hóa đơn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }else {
                Toast.makeText(getContext(), "Xe không thuộc tài khoản", Toast.LENGTH_SHORT).show();
            }
        }
    });

}
