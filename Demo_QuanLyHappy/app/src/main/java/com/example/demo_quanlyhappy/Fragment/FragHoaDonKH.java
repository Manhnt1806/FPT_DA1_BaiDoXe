package com.example.demo_quanlyhappy.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.Adapter.CheckNapTienAdapter;
import com.example.demo_quanlyhappy.Adapter.HoaDonAdapter;
import com.example.demo_quanlyhappy.Adapter.NapTienAdapter;
import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.R;

import java.util.ArrayList;

public class FragHoaDonKH extends Fragment {
    RecyclerView rcv;
    HoaDonDAO dao;
    ArrayList<HoaDon> list =new ArrayList<>();
    HoaDonAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_hoadon_luot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        KhachHang kh = (KhachHang) getArguments().get("quyen112");
        rcv = view.findViewById(R.id.rcv_hoadonLuot);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new HoaDonDAO(getContext());
//        list = dao.getIDKH(String.valueOf(kh.getIdKH()));
        adapter = new HoaDonAdapter(getContext(), list);
        rcv.setAdapter(adapter);
    }
}
