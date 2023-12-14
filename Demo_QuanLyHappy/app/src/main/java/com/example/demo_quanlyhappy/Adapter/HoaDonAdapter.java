package com.example.demo_quanlyhappy.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.Fragment.FragQuetNV;
import com.example.demo_quanlyhappy.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder> {
    Context context;
    ArrayList<HoaDon> list;
    HoaDonDAO dao;
    KhachHangDAO daoKH;
    NhanVienDAO daoNV;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        dao = new HoaDonDAO(context);
        daoKH = new KhachHangDAO(context);
        daoNV = new NhanVienDAO(context);

    }
    @NonNull
    @Override
    public HoaDonAdapter.HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_hoadon, parent, false);
        HoaDonAdapter.HoaDonViewHolder viewHolder = new HoaDonAdapter.HoaDonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.HoaDonViewHolder holder, int position) {
        HoaDon hd = list.get(position);
        int idKH = hd.getIdKH();

        KhachHang kh = daoKH.getID2(String.valueOf(idKH));
        if(hd.getStatusHD()==1){
            holder.tvStatusHD.setTextColor(Color.GREEN);
            holder.tvStatusHD.setText("Đã thanh toán");
        }else{
            holder.tvStatusHD.setTextColor(Color.RED);
            holder.tvStatusHD.setText("Chưa thanh toán");
        }
        holder.tvsdtKH.setText(kh.getSdtKH());
        if(hd.getStatusHD()==0){
            holder.tvtgVao.setText(simpleDateFormat.format(hd.getTgVao()));
            Bitmap bitmap = BitmapFactory.decodeByteArray(hd.haVao, 0, hd.haVao.length);
            holder.imgHaRaVao.setImageBitmap(bitmap);
        }else{
            holder.tvtgVao.setText(simpleDateFormat.format(hd.getTgVao()));
            holder.tvtgRa.setText(simpleDateFormat.format(hd.getTgRa()));
            Bitmap bitmap = BitmapFactory.decodeByteArray(hd.haRa, 0, hd.haRa.length);
            holder.imgHaRaVao.setImageBitmap(bitmap);
        }
        if(hd.getHtTT()==0){
            holder.tvHtttHD.setText("Chuyển khoản");
        }else {
            holder.tvHtttHD.setText("Tiền mặt");
        }
        holder.tvHoaDon.setText("Hóa đơn: "+hd.getTienTT()+"VND");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView tvtgVao, tvtgRa, tvsdtKH, tvHoaDon, tvHtttHD, tvStatusHD;
        ImageView imgHaRaVao;
        public HoaDonViewHolder(View view) {
            super(view);
            tvtgVao = view.findViewById(R.id.tv_tgVao);
            tvtgRa = view.findViewById(R.id.tv_tgRa);
            tvsdtKH = view.findViewById(R.id.tv_sdtHD);
            tvHoaDon = view.findViewById(R.id.tv_tienTT);
            tvHtttHD = view.findViewById(R.id.tv_htttHD);
            tvStatusHD = view.findViewById(R.id.tv_statusHD);
            imgHaRaVao = view.findViewById(R.id.img_haRaVaoHD);
        }
    }
}
