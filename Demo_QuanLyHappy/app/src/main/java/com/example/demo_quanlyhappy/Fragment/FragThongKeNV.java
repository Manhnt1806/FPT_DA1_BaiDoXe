package com.example.demo_quanlyhappy.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

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
import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.HoaDonVe;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FragThongKeNV extends Fragment {
    RecyclerView rcv;
    HoaDonDAO dao;
    HoaDonVeDAO daoHDV;
    ArrayList<HoaDon> list =new ArrayList<>();
    ArrayList<HoaDonVe> listHDV =new ArrayList<>();
    HoaDonAdapter adapter;
    HoaDonVeAdapter adapterHDV;
    TextView tvDoanhThu, tvTheoLuot, tvDKve, tvTuNgay, tvDenNgay;
    Button btnDoanhThu;
    int mY, mM, mD;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_thongke_nv, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_thongkeQL);
        tvDoanhThu = view.findViewById(R.id.tv_doanhThu);
        tvTheoLuot = view.findViewById(R.id.tv_theoLuot);
        tvDKve = view.findViewById(R.id.tv_DKve);
        tvTuNgay = view.findViewById(R.id.tv_tuNgay);
        tvDenNgay = view.findViewById(R.id.tv_denNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new HoaDonDAO(getContext());
        daoHDV = new HoaDonVeDAO(getContext());
        list = dao.getAll();
        listHDV = daoHDV.getAll();
        adapter = new HoaDonAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        tvTheoLuot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheoLuot.setTextColor(Color.BLUE);
                tvDKve.setTextColor(Color.WHITE);
                list = dao.getAll();
                adapter = new HoaDonAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });
        tvDKve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDKve.setTextColor(Color.BLUE);
                tvTheoLuot.setTextColor(Color.WHITE);
                listHDV = daoHDV.getAll();
                adapterHDV = new HoaDonVeAdapter(getContext(), listHDV);
                rcv.setAdapter(adapterHDV);
            }
        });
        tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, mTuNgay, mY, mM, mD);
                d.show();
            }
        });
        tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getContext(), 0, mDenNgay, mY, mM, mD);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = tvTuNgay.getText().toString();
                String denNgay = tvDenNgay.getText().toString();
                int dtHD = dao.DoanhThu(tuNgay, denNgay);
                int dtHDV = daoHDV.DoanhThu(tuNgay, denNgay);
                int doanhThu = dtHD + dtHDV;
                tvDoanhThu.setText("Doanh thu: "+doanhThu+"VNĐ");
                tvTheoLuot.setText("Theo lượt: "+dtHD+"VND");
                tvDKve.setText("Đăng ký vé: "+dtHDV+"VND");
            }
        });
        int dtTheoLuot = 0;
        int dtDKve = 0;
        for(int i=0; i<list.size(); i++){
            dtTheoLuot += list.get(i).getTienTT();
        }
        for(int i=0; i<listHDV.size(); i++){
            dtDKve += listHDV.get(i).getTienTTve();
        }
        int doanhThu = dtTheoLuot+dtDKve;
        tvDoanhThu.setText("Doanh thu: "+doanhThu+"VND");
        tvTheoLuot.setText("Theo lượt: "+dtTheoLuot+"VND");
        tvDKve.setText("Đăng ký vé: "+dtDKve+"VND");
    }
    DatePickerDialog.OnDateSetListener mTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            mY = y;
            mM = m;
            mD = d;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            tvTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            mY = y;
            mM = m;
            mD = d;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            tvDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}
