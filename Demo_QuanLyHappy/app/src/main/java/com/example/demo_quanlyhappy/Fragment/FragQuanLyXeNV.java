package com.example.demo_quanlyhappy.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.Adapter.XeAdapter;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.R;

import java.util.ArrayList;

public class FragQuanLyXeNV extends Fragment {
    RecyclerView rcv;
    XeDAO dao;
    KhachHangDAO daoKH;
    ArrayList<Xe> list =new ArrayList<>();
    ArrayList<KhachHang> listKH =new ArrayList<>();
    ArrayList<KhachHang> listKH2 =new ArrayList<>();
    ArrayList<KhachHang> listKH0 =new ArrayList<>();
    XeAdapter adapter;
    TextView tvTheHoatDong, tvTheDangGui, tvTheDangVang, tvNgungHD;
    private SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quanlyxe_nv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = view.findViewById(R.id.rcv_thexeNV);
        tvTheHoatDong = view.findViewById(R.id.tv_theHdNV);
        tvTheDangGui = view.findViewById(R.id.tv_theDangGuiNV);
        tvTheDangVang = view.findViewById(R.id.tv_TheDangVangNV);
        tvNgungHD = view.findViewById(R.id.tv_theNgungHDNV);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        dao = new XeDAO(getContext());
        list = dao.getAll();
        adapter = new XeAdapter(getContext(), list);
        rcv.setAdapter(adapter);
        thongKeThe();
        tvTheHoatDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTheHoatDong.setTextColor(Color.BLUE);
                tvTheDangGui.setTextColor(Color.WHITE);
                tvTheDangVang.setTextColor(Color.WHITE);
                tvNgungHD.setTextColor(Color.WHITE);
                list = dao.getDangSd2(String.valueOf(0));
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
                list = dao.getTrong(String.valueOf(0));
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
                list = dao.getTrong(String.valueOf(1));
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
                list = dao.getDangSd2(String.valueOf(1));
                adapter = new XeAdapter(getContext(), list);
                rcv.setAdapter(adapter);
            }
        });
    }
    public void thongKeThe(){
        int theHD = 0;
        int theDG = 0;
        int theDV = 0;
        int theNHD = 0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getStatusXe()==0)
                theHD += 1;
            if(list.get(i).getStatusXe()==0&&list.get(i).getStatusGuiXe()==0)
                theDG += 1;
            if(list.get(i).getStatusXe()==0&&list.get(i).getStatusGuiXe()==1)
                theDV += 1;
            if(list.get(i).getStatusXe()==1)
                theNHD += 1;
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText );
                return false;
            }
        });
    }
}
