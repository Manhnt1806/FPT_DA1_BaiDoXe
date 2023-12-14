package com.example.demo_quanlyhappy.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.Adapter.XeAdapter;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragQuanLyXeKH extends Fragment {
    RecyclerView rcv;
    XeDAO dao;
    KhachHangDAO daoKH;
    ArrayList<Xe> list =new ArrayList<>();
    ArrayList<KhachHang> listKH =new ArrayList<>();
    ArrayList<KhachHang> listKH2 =new ArrayList<>();
    ArrayList<KhachHang> listKH0 =new ArrayList<>();
    XeAdapter adapter;
    Button btnAdd;
    ImageView imgDangKy;
    private int REQUEST_CODE;
    TextView tvTheHoatDong, tvTheDangGui, tvTheDangVang, tvNgungHD;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlyxe_kh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daoKH = new KhachHangDAO(getContext());
        KhachHang kh = (KhachHang) getArguments().get("quyen3");
        KhachHang kh1 = daoKH.getID2(String.valueOf(kh.getIdKH()));
        rcv = view.findViewById(R.id.rcv_thexe);
        btnAdd = view.findViewById(R.id.btn_addThe);
        tvTheHoatDong = view.findViewById(R.id.tv_theHd);
        tvTheDangGui = view.findViewById(R.id.tv_theDangGui);
        tvTheDangVang = view.findViewById(R.id.tv_TheDangVang);
        tvNgungHD = view.findViewById(R.id.tv_theNgungHD);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new XeDAO(getContext());
        list = dao.getDangSd1(String.valueOf(kh1.getIdKH()), String.valueOf(0));
        adapter = new XeAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        thongKeThe();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.thexe_add, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edBienSo = view.findViewById(R.id.ed_bien_so);
                EditText edTenXem = view.findViewById(R.id.ed_ten_xe_may);
                imgDangKy = view.findViewById(R.id.imgDangKy);
                Button btnAddNT = view.findViewById(R.id.btn_add_tx);

                imgDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,REQUEST_CODE);
                    }
                });
                btnAddNT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int idKH = kh1.getIdKH();
                        String tenXe = edTenXem.getText().toString();
                        String bienSo = edBienSo.getText().toString();
                        byte[] anhDangKy = ConverttoArrayByte(imgDangKy);
                        int statusXe = 0;
                        int idNV = 0;
                        int statusGuiXe = 1;
                        Xe x = new Xe(idKH, tenXe, bienSo, anhDangKy, statusXe, idNV, statusGuiXe);
                        if(dao.Insert(x)){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao.getDangSd1(String.valueOf(kh1.getIdKH()), String.valueOf(0)));
                            thongKeThe();
                            dialog.dismiss();
                            adapter.notifyDataSetChanged();

                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        tvTheHoatDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheHoatDong.setTextColor(Color.BLUE);
                tvTheDangGui.setTextColor(Color.WHITE);
                tvTheDangVang.setTextColor(Color.WHITE);
                tvNgungHD.setTextColor(Color.WHITE);
                list = dao.getDangSd1(String.valueOf(kh1.getIdKH()), String.valueOf(0));
                adapter = new XeAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });
        tvTheDangGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheHoatDong.setTextColor(Color.WHITE);
                tvTheDangGui.setTextColor(Color.BLUE);
                tvTheDangVang.setTextColor(Color.WHITE);
                tvNgungHD.setTextColor(Color.WHITE);
                list = dao.getDangGui(String.valueOf(kh1.getIdKH()), String.valueOf(0));
                adapter = new XeAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });
        tvTheDangVang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheHoatDong.setTextColor(Color.WHITE);
                tvTheDangGui.setTextColor(Color.WHITE);
                tvTheDangVang.setTextColor(Color.BLUE);
                tvNgungHD.setTextColor(Color.WHITE);
                list = dao.getDangGui(String.valueOf(kh1.getIdKH()), String.valueOf(1));
                adapter = new XeAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });
        tvNgungHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheHoatDong.setTextColor(Color.WHITE);
                tvTheDangGui.setTextColor(Color.WHITE);
                tvTheDangVang.setTextColor(Color.WHITE);
                tvNgungHD.setTextColor(Color.BLUE);
                list = dao.getDangSd1(String.valueOf(kh1.getIdKH()), String.valueOf(1));
                adapter = new XeAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgDangKy.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    public void thongKeThe(){
        KhachHang kh = (KhachHang) getArguments().get("quyen3");
        KhachHang kh1 = daoKH.getID2(String.valueOf(kh.getIdKH()));
        int theHD = 0;
        int theDG = 0;
        int theDV = 0;
        int theNHD = 0;
        for(int i=0; i<dao.getDangSd1(String.valueOf(kh1.getIdKH()), String.valueOf(0)).size(); i++){
                theHD += 1;
        }
        for(int i=0; i<dao.getDangSd1(String.valueOf(kh1.getIdKH()), String.valueOf(1)).size(); i++){
            theNHD += 1;
        }
        for(int i=0; i<dao.getDangGui(String.valueOf(kh1.getIdKH()), String.valueOf(0)).size(); i++){
            theDG += 1;
        }
        for(int i=0; i<dao.getDangGui(String.valueOf(kh1.getIdKH()), String.valueOf(1)).size(); i++){
            theDV += 1;
        }
        daoKH = new KhachHangDAO(getContext());
        listKH = daoKH.getDKve(String.valueOf(1));
        listKH2 = daoKH.getDKve(String.valueOf(2));
        listKH0 = daoKH.getDKve(String.valueOf(0));
        int Trong1 = 0;
        int Trong2 = 0;
        int Trong0 = 0;
        for(int i=0; i<listKH.size(); i++){
            if(String.valueOf(listKH.get(i).getIdKH())!=null) {
                list = dao.getDangSd1(String.valueOf(listKH.get(i).getIdKH()), String.valueOf(0));
                for (int j = 0; j < list.size(); j++) {
                    Trong1 += 1;
                }
            }
        }
        for(int i=0; i<listKH2.size(); i++){
            if(String.valueOf(listKH2.get(i).getIdKH())!=null){
                list = dao.getDangSd1(String.valueOf(listKH2.get(i).getIdKH()), String.valueOf(0));
                for(int j=0; j<list.size(); j++){
                    Trong1 += 1;
                }
            }

        }
        for(int i=0; i<listKH0.size(); i++){
            if(String.valueOf(listKH0.get(i).getIdKH())!=null){
                list = dao.getDangGui(String.valueOf(listKH0.get(i).getIdKH()), String.valueOf(0));
                for(int j=0; j<list.size(); j++){
                    Trong0 += 1;
                }
            }
        }
        int Trong = 100 - (Trong1 + Trong2 + Trong0);
        tvTheHoatDong.setText("Thẻ đang hoạt động: "+theHD);
        tvTheDangGui.setText("Thẻ đang gửi: "+theDG+" - Trống: "+Trong);
        tvTheDangVang.setText("Thẻ đang vắng: "+theDV);
        tvNgungHD.setText("Thẻ ngừng hoạt động: "+theNHD);
        adapter.notifyDataSetChanged();
    }
}
