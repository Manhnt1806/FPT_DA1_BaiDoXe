package com.example.demosqlwithimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CusAdapter extends ArrayAdapter<SanPham> {

    CusAdapter(Context context, int resource, List<SanPham> item) {
        super(context, resource, item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if (view ==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            view=inflater.inflate(R.layout.row_listiew, null);
        }
        SanPham sanPham=getItem(position);
        if (sanPham!=null)
        {
            ImageView imgHinhDaidien= (ImageView) view.findViewById(R.id.imgAnhDaiDien);
            TextView txtTenSP= (TextView) view.findViewById(R.id.txtTen);

            Bitmap bitmap= BitmapFactory.decodeByteArray(sanPham.hinhanh, 0, sanPham.hinhanh.length);
            imgHinhDaidien.setImageBitmap(bitmap);
            txtTenSP.setText(sanPham.tensp);
        }
        return view;
    }
}
