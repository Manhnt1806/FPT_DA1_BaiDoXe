package com.example.demo_quanlyhappy.Adapter;

import static android.content.Intent.getIntent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.MainChinh;
import com.example.demo_quanlyhappy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NapTienAdapter extends RecyclerView.Adapter<NapTienAdapter.NapTienViewHolder> {
    Context context;
    ArrayList<NapTien> list;
    NapTienDAO dao;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public NapTienAdapter(Context context, ArrayList<NapTien> list) {
        this.context = context;
        this.list = list;
        dao = new NapTienDAO(context);
    }
    @NonNull
    @Override
    public NapTienAdapter.NapTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.cv_naptien, parent, false);
        NapTienAdapter.NapTienViewHolder viewHolder = new NapTienAdapter.NapTienViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NapTienAdapter.NapTienViewHolder holder, int position) {
        NapTien nt = list.get(position);
        if(nt.getStatusNT()==0){
            holder.tvStatusNT.setTextColor(Color.RED);
            holder.tvStatusNT.setText("Đang xử lý");
        }else{
            holder.tvStatusNT.setTextColor(Color.BLUE);
            holder.tvStatusNT.setText("Hoàn tất");
        }
        if(nt.getHtttNT()==0){
            holder.tvhtttNT.setText("Chuyển khoản");
        }else{
            holder.tvhtttNT.setText("Tiền mặt");
        }
        holder.tvNgayNap.setText(simpleDateFormat.format(nt.getNgayNap()));
        holder.tvTienNap.setText("+" + nt.getSoTien());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NapTienViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgayNap, tvStatusNT, tvhtttNT, tvTienNap;
        CardView cvNapTien;
        public NapTienViewHolder(@NonNull View view) {
            super(view);
            tvNgayNap = view.findViewById(R.id.tv_ngay_nap);
            tvStatusNT = view.findViewById(R.id.tv_statusNT);
            tvhtttNT = view.findViewById(R.id.tv_htttNTT);
            tvTienNap = view.findViewById(R.id.tv_tien_nap);
            cvNapTien = view.findViewById(R.id.cardview_nap_tien);
        }
    }
}
