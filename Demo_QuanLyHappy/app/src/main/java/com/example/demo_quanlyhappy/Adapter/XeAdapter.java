package com.example.demo_quanlyhappy.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XeAdapter extends RecyclerView.Adapter<XeAdapter.XeViewHolder> implements Filterable {
    Context context;
    ArrayList<Xe> list;
    ArrayList<Xe> mList;
    XeDAO dao;
    public XeAdapter(Context context, ArrayList<Xe> list) {
        this.context = context;
        this.list = list;
        this.mList = list;
        dao = new XeDAO(context);
    }
    @NonNull
    @Override
    public XeAdapter.XeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_xe, parent, false);
        XeAdapter.XeViewHolder viewHolder = new XeAdapter.XeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull XeAdapter.XeViewHolder holder, int position) {
        Xe x = list.get(position);
        if(x.getStatusGuiXe()==0){
            holder.tvStatusGuiXe.setTextColor(Color.RED);
            holder.tvStatusGuiXe.setText("Đang gửi");
        }else{
            holder.tvStatusGuiXe.setTextColor(Color.BLUE);
            holder.tvStatusGuiXe.setText("Đang vắng");
        }
        holder.tvTenXe.setText("Tên xe: "+x.getTenXe());
        holder.tvBienSo.setText("Biển số: "+x.getBienSoXe());
            Bitmap bitmap = BitmapFactory.decodeByteArray(x.dangKyXe, 0, x.dangKyXe.length);
            holder.imgDangKy.setImageBitmap(bitmap);

        if(x.getStatusXe()==0){
            holder.chkStatusXe.setText("Đang hoạt động");
        }else{
            holder.chkStatusXe.setText("Ngưng hoạt động");
        }
        holder.cvXe.setOnClickListener(new View.OnClickListener() {
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
                TextView tvBS = view.findViewById(R.id.tv_didong);;
                CheckBox chkSttNV = view.findViewById(R.id.chk_sttNV);

                tvTenNV.setText(x.getTenXe());
                tvSdtNV.setText(x.getBienSoXe());
                tvBS.setText("Biển số");
                if(x.getStatusXe()==0){
                    chkSttNV.setChecked(true);
                }else {
                    chkSttNV.setChecked(false);
                }
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(x.getStatusGuiXe()==0){
                            Toast.makeText(context, "Xe đang gửi, Udate thất bại", Toast.LENGTH_SHORT).show();
                        }else{
                            if(chkSttNV.isChecked()){
                                x.setStatusXe(0);
                                if (dao.UpdateSttGX(x)) {
                                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    list.clear();
                                    list.addAll(dao.getDangSd1(String.valueOf(x.getIdKH()), String.valueOf(0)));
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                x.setStatusXe(1);
                                if (dao.UpdateSttGX(x)) {
                                    Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    list.clear();
                                    list.addAll(dao.getDangSd1(String.valueOf(x.getIdKH()), String.valueOf(0)));
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                                }
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    list = mList;
                }else {
                    ArrayList<Xe> mListOld = new ArrayList<>();
                    for (Xe xe: mList){
                        if(xe.getBienSoXe().toLowerCase().contains(strSearch.toLowerCase())){
                            mListOld.add(xe);
                        }
                    }
                    list = mListOld;
                }
                FilterResults results = new FilterResults();
                results.values = list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<Xe>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class XeViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatusGuiXe, tvTenXe, tvBienSo, chkStatusXe;
        CardView cvXe;

        ImageView imgDangKy;
        public XeViewHolder(View view) {
            super(view);
            tvStatusGuiXe = view.findViewById(R.id.tv_statusGuiXe);
            tvTenXe = view.findViewById(R.id.tv_tenXe);
            tvBienSo = view.findViewById(R.id.tv_bienSoXe);
            chkStatusXe = view.findViewById(R.id.chk_statusXe);
            imgDangKy = view.findViewById(R.id.img_haRaVao);
            cvXe = view.findViewById(R.id.cardview_xe);

        }
    }
}
