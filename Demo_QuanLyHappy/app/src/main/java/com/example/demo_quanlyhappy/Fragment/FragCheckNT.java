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
import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.R;

import java.util.ArrayList;

public class FragCheckNT extends Fragment {
    RecyclerView rcv;
    NapTienDAO dao;
    ArrayList<NapTien> list =new ArrayList<>();
    CheckNapTienAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_check_naptien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_checkNapTien);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new NapTienDAO(getContext());
        list = dao.getCheckNT(String.valueOf(0));
        adapter = new CheckNapTienAdapter(getContext(), list);
        rcv.setAdapter(adapter);
    }
}
