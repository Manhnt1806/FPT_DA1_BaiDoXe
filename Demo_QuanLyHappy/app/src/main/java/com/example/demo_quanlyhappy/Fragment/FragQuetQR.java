package com.example.demo_quanlyhappy.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo_quanlyhappy.Adapter.HoaDonAdapter;
import com.example.demo_quanlyhappy.Adapter.XeAdapter;
import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragQuetQR extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quet_qr, container, false);

    }

}
