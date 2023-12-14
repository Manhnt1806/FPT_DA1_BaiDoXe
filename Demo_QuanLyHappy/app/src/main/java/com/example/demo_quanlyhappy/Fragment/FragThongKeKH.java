package com.example.demo_quanlyhappy.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.Adapter.HoaDonAdapter;
import com.example.demo_quanlyhappy.Adapter.HoaDonVeAdapter;
import com.example.demo_quanlyhappy.Adapter.NapTienAdapter;
import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.HoaDonVeDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.HoaDonVe;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragThongKeKH extends Fragment {
    RecyclerView rcv;
    NapTienDAO dao;
    ArrayList<NapTien> list =new ArrayList<>();
    HoaDonDAO daoHD;
    KhachHangDAO daoKH;
    HoaDonVeDAO daoHDV;
    ArrayList<HoaDon> listHD =new ArrayList<>();
    ArrayList<HoaDonVe> listHDV =new ArrayList<>();
    NapTienAdapter adapter;
    HoaDonAdapter adapterHD;
    HoaDonVeAdapter adapterHDV;
    Button btnAdd;
    TextView tvSoDu, tvTongNap, tvThanhToan,tvThanhToanVe;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_thongke_kh, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daoKH = new KhachHangDAO(getContext());
        KhachHang kh = (KhachHang) getArguments().get("quyen2");
        KhachHang kh1 = daoKH.getID2(String.valueOf(kh.getIdKH()));
        rcv = view.findViewById(R.id.rcv_naptien);
        btnAdd = view.findViewById(R.id.btn_addNT);
        tvTongNap = view.findViewById(R.id.tv_tongNap);
        tvThanhToan = view.findViewById(R.id.tv_daThanhToan);
        tvThanhToanVe = view.findViewById(R.id.tv_daTTve);
        tvSoDu = view.findViewById(R.id.tv_soDu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new NapTienDAO(getContext());
        list = dao.getNapTien(String.valueOf(kh1.getIdKH()));
        adapter = new NapTienAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        daoHD = new HoaDonDAO(getContext());
        daoHDV = new HoaDonVeDAO(getContext());
        int daTTHD = 0;
        int daTTHDV = 0;
        listHD = daoHD.getIDKH(String.valueOf(kh1.getIdKH()));
        listHDV = daoHDV.getIDKH(String.valueOf(kh1.getIdKH()));
        for (int i=0; i<listHD.size(); i++){
            daTTHD += listHD.get(i).getTienTT();
        }
        for (int i=0; i<listHDV.size(); i++){
            daTTHDV += listHDV.get(i).getTienTTve();
        }
        tvThanhToan.setText("Hóa đơn theo lượt: "+daTTHD+"VND");
        tvThanhToanVe.setText("Đăng ký vé: "+daTTHDV+"VND");
        tongNap();
        soDu();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.naptien_add, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                TextView tvedNgayNap = view.findViewById(R.id.tv_addngaynap);
                EditText edTienNap = view.findViewById(R.id.ed_tien_nap);
                Button btnAddNT = view.findViewById(R.id.btn_add_nt);
                RadioButton rdoChuyenKhoan = view.findViewById(R.id.rdo_chuyenkhoan);
                RadioButton rdoTienMat = view.findViewById(R.id.rdo_tienmat);

                Date objDate = new Date(System.currentTimeMillis());
                DateFormat dateFormat = new DateFormat();
                String chuoi_ngay_thang_nam = (String) dateFormat.format("yyyy/MM/dd", objDate);
                tvedNgayNap.setText(chuoi_ngay_thang_nam);
                btnAddNT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tienNap = Integer.parseInt(edTienNap.getText().toString());
                        Date ngayNap = null;
                        try {
                            ngayNap = sdf.parse(tvedNgayNap.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        int idKH = kh1.getIdKH();
                        int statusNT = 0;
                        int htttNT = 0;
                        if(rdoChuyenKhoan.isChecked()){
                            htttNT = 0;
                        }else if(rdoTienMat.isChecked()) {
                            htttNT = 1;
                        }
                        NapTien nt = new NapTien(idKH, htttNT, tienNap, ngayNap, statusNT);
                        if(dao.Insert(nt)){
                            Toast.makeText(getContext(), "Chờ xử lý", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(dao.getNapTien(String.valueOf(idKH)));
                            tongNap();
                            soDu();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(getContext(), "Nạp tiền thất bại", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        tvThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvThanhToan.setTextColor(Color.BLUE);
                tvThanhToanVe.setTextColor(Color.WHITE);
                tvTongNap.setTextColor(Color.WHITE);
                listHD = daoHD.getIDKH(String.valueOf(kh1.getIdKH()));
                adapterHD = new HoaDonAdapter(getContext(), listHD);
                rcv.setAdapter(adapterHD);
            }
        });
        tvThanhToanVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvThanhToanVe.setTextColor(Color.BLUE);
                tvThanhToan.setTextColor(Color.WHITE);
                tvTongNap.setTextColor(Color.WHITE);
                listHDV = daoHDV.getIDKH(String.valueOf(kh1.getIdKH()));
                adapterHDV = new HoaDonVeAdapter(getContext(), listHDV);
                rcv.setAdapter(adapterHDV);
            }
        });
        tvTongNap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTongNap.setTextColor(Color.BLUE);
                tvThanhToanVe.setTextColor(Color.WHITE);
                tvThanhToan.setTextColor(Color.WHITE);
                list = dao.getNapTien(String.valueOf(kh1.getIdKH()));
                adapter = new NapTienAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });
    }
    public void tongNap(){
        int tongNap = 0;
        KhachHang kh = (KhachHang) getArguments().get("quyen2");
        KhachHang kh1 = daoKH.getID2(String.valueOf(kh.getIdKH()));
        ArrayList<NapTien> list1 = dao.tongNap(String.valueOf(kh1.getIdKH()), String.valueOf(1));
        for(int i=0; i<list1.size(); i++){
            tongNap += list1.get(i).getSoTien();
        }
        tvTongNap.setText("Tổng nạp: "+tongNap+"VND");
    }
    public void soDu(){
        KhachHang kh = (KhachHang) getArguments().get("quyen2");
        KhachHang kh1 = daoKH.getID2(String.valueOf(kh.getIdKH()));
        listHD = daoHD.getIDKH(String.valueOf(kh1.getIdKH()));
        listHDV = daoHDV.getIDKH(String.valueOf(kh1.getIdKH()));
        daoKH = new KhachHangDAO(getContext());
        int tongNap = 0;
        ArrayList<NapTien> list1 = dao.tongNap(String.valueOf(kh1.getIdKH()), String.valueOf(1));
        for(int i=0; i<list1.size(); i++){
            tongNap += list1.get(i).getSoTien();
        }
        int daTTHD = 0;
        int daTTHDV = 0;
        for (int i=0; i<listHD.size(); i++){
            daTTHD += listHD.get(i).getTienTT();
        }
        for (int i=0; i<listHDV.size(); i++){
            daTTHDV += listHDV.get(i).getTienTTve();
        }
        int daTT = daTTHD + daTTHDV;
        int soDu = tongNap - daTT;
        kh1.setSoDuKH(soDu);
        daoKH.UpdateSoDu(kh1);
        tvSoDu.setText("Số dư: "+kh1.getSoDuKH()+"VND");
    }
}
