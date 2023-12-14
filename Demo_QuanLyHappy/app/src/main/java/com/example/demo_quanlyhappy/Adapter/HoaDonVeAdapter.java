package com.example.demo_quanlyhappy.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.HoaDonVeDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.HoaDonVe;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonVeAdapter extends RecyclerView.Adapter<HoaDonVeAdapter.HoaDonVeViewHolder> {
    Context context;
    ArrayList<HoaDonVe> list;
    HoaDonVeDAO dao;
    KhachHangDAO daoKH;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public HoaDonVeAdapter(Context context, ArrayList<HoaDonVe> list) {
        this.context = context;
        this.list = list;
        dao = new HoaDonVeDAO(context);
        daoKH = new KhachHangDAO(context);

    }
    @NonNull
    @Override
    public HoaDonVeAdapter.HoaDonVeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_hoadonve, parent, false);
        HoaDonVeAdapter.HoaDonVeViewHolder viewHolder = new HoaDonVeAdapter.HoaDonVeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonVeAdapter.HoaDonVeViewHolder holder, int position) {
        HoaDonVe hd = list.get(position);
        int idKH = hd.getIdKH();

        holder.tvtgDK.setText(simpleDateFormat.format(hd.getTgTTve()));
        if(hd.getGoiSD()==1){
            holder.tvGoiDK.setText("Vé tháng");
        }else if(hd.getGoiSD()==2){
            holder.tvGoiDK.setText("Vé năm");
        }
        holder.tvTienDK.setText(hd.getTienTTve()+"VND");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoaDonVeViewHolder extends RecyclerView.ViewHolder {
        TextView tvtgDK, tvGoiDK, tvTienDK;
        public HoaDonVeViewHolder(View view) {
            super(view);
            tvtgDK = view.findViewById(R.id.tv_ngay_ttVe);
            tvGoiDK = view.findViewById(R.id.tv_VeDK);
            tvTienDK = view.findViewById(R.id.tv_tien_TTve);
        }
    }
}
