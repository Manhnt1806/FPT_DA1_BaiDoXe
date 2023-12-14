package com.example.demo_quanlyhappy.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {
    Context context;
    ArrayList<NhanVien> list;
    NhanVienDAO dao;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> list) {
        this.context = context;
        this.list = list;
        dao = new NhanVienDAO(context);
    }
    @NonNull
    @Override
    public NhanVienAdapter.NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.cv_taikhoan_nv, parent, false);
        NhanVienAdapter.NhanVienViewHolder viewHolder = new NhanVienAdapter.NhanVienViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdapter.NhanVienViewHolder holder, int position) {
        NhanVien nv = list.get(position);
        if(nv.getStatusNV()==0){
            holder.tvStatusNV.setTextColor(Color.RED);
            holder.tvStatusNV.setText("Ngưng hoạt động");
        }else{
            holder.tvStatusNV.setTextColor(Color.BLUE);
            holder.tvStatusNV.setText("Đang hoạt động");
        }
        holder.tvTenNV.setText(nv.getTenNV());
        holder.tvSdtNV.setText(nv.getSdtNV());
        holder.cvNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_stt_nhanvien, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                ImageView imgBack = view.findViewById(R.id.btn_back_QLNV);
                Button btnCancel = view.findViewById(R.id.btn_huyQLNV);
                Button btnSave = view.findViewById(R.id.btn_saveQLNV);
                TextView tvTenNV = view.findViewById(R.id.tv_ten_NV_QL);
                TextView tvSdtNV = view.findViewById(R.id.tv_sdt_NV_QL);
                CheckBox chkSttNV = view.findViewById(R.id.chk_sttNV);

                tvTenNV.setText(nv.getTenNV());
                tvSdtNV.setText(nv.getSdtNV());
                if(nv.getStatusNV()==1){
                    chkSttNV.setChecked(true);
                }else {
                    chkSttNV.setChecked(false);
                }
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(chkSttNV.isChecked()){
                            nv.setStatusNV(1);
                            if (dao.Update(nv)) {
                                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                list.clear();
                                list.addAll(dao.getChucVu("Nhân viên"));
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            nv.setStatusNV(0);
                            if (dao.Update(nv)) {
                                Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                list.clear();
                                list.addAll(dao.getAll());
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
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

    public class NhanVienViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenNV, tvStatusNV, tvSdtNV;
        CardView cvNhanVien;
        public NhanVienViewHolder(@NonNull View view) {
            super(view);
            tvTenNV = view.findViewById(R.id.tv_ten_QLNV);
            tvStatusNV = view.findViewById(R.id.tv_statusNV);
            tvSdtNV = view.findViewById(R.id.tv_sdt_QLNV);
            cvNhanVien = view.findViewById(R.id.cardview_nhanvien);
        }
    }
}
