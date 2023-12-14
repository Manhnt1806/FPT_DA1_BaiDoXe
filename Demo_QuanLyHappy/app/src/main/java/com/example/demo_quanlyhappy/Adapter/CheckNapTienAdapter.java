package com.example.demo_quanlyhappy.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NapTienDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CheckNapTienAdapter extends RecyclerView.Adapter<CheckNapTienAdapter.NapTienViewHolder> {
    Context context;
    ArrayList<NapTien> list;
    NapTienDAO dao;
    KhachHangDAO daoKH;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public CheckNapTienAdapter(Context context, ArrayList<NapTien> list) {
        this.context = context;
        this.list = list;
        dao = new NapTienDAO(context);
        daoKH = new KhachHangDAO(context);
    }

    @NonNull
    @Override
    public CheckNapTienAdapter.NapTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.cv_naptien, parent, false);
        CheckNapTienAdapter.NapTienViewHolder viewHolder = new CheckNapTienAdapter.NapTienViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckNapTienAdapter.NapTienViewHolder holder, int position) {
        NapTien nt = list.get(position);
        KhachHang kh = daoKH.getID2(String.valueOf(nt.getIdKH()));
        if(nt.getStatusNT()==0){
            holder.tvStatusNT.setTextColor(Color.RED);
            holder.tvStatusNT.setText("Đang xử lý");
        }else{
            holder.tvStatusNT.setTextColor(Color.BLUE);
            holder.tvStatusNT.setText("Hoàn tất");
        }
            holder.tvhtttNT.setText(kh.getTenKH());
        holder.tvNgayNap.setText(simpleDateFormat.format(nt.getNgayNap()));
        holder.tvTienNap.setText("+" + nt.getSoTien());
        holder.cvNapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_checknaptien, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                ImageView imgBack = view.findViewById(R.id.btn_back_checkNapTien);
                Button btnCancel = view.findViewById(R.id.btn_huyCheckNT);
                Button btnSave = view.findViewById(R.id.btn_saveCheckNT);
                TextView tvTenKH = view.findViewById(R.id.tv_ten_KH_check);
                TextView tvSdtKH = view.findViewById(R.id.tv_sdt_KH_check);
                TextView tvTienNap = view.findViewById(R.id.tv_checkTienNap);
                TextView tvHttt = view.findViewById(R.id.tv_httt_checkNT);

                tvTenKH.setText(kh.getTenKH());
                tvSdtKH.setText(kh.getSdtKH());
                tvTienNap.setText(nt.getSoTien()+"");
                if(nt.getHtttNT()==0){
                    tvHttt.setText("Chuyển khoản");
                }else{
                    tvHttt.setText("Tiền mặt");
                }

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(nt.getHtttNT()==1){
                            Toast.makeText(context, "Giao dịch đã xác nhận", Toast.LENGTH_SHORT).show();
                        }else {
                            nt.setStatusNT(1);
                            nt.setSoTien(Integer.parseInt(tvTienNap.getText().toString()));
                            if (dao.UpdateSttNT(nt)) {
                                Toast.makeText(context, "Xác nhận thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                list.clear();
                                list.addAll(dao.getCheckNT(String.valueOf(0)));
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context, "Xác nhận thất bại", Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                });
                imgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
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
