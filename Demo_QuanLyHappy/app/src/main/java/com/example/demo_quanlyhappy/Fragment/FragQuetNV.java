package com.example.demo_quanlyhappy.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.demo_quanlyhappy.DAO.HoaDonDAO;
import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DAO.XeDAO;
import com.example.demo_quanlyhappy.DTO.HoaDon;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.DTO.Xe;
import com.example.demo_quanlyhappy.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragQuetNV extends Fragment {
    ImageView imgQR;
    Button btnQuet;
    XeDAO daoXe;
    HoaDonDAO daoHD;
    KhachHangDAO daoKH;
    NhanVienDAO daoNV;
    ArrayList<HoaDon> list = new ArrayList<>();
    ArrayList<Xe> listXe = new ArrayList<>();
    ArrayList<KhachHang> listKH = new ArrayList<>();
    ArrayList<KhachHang> listKH2 = new ArrayList<>();
    ArrayList<KhachHang> listKH0 = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private int REQUEST_CODE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_quet_qr, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NhanVien nv = (NhanVien) getArguments().get("quyen123");
        imgQR = view.findViewById(R.id.imgQR);
        btnQuet = view.findViewById(R.id.btn_quet);
        daoKH = new KhachHangDAO(getContext());
        daoXe = new XeDAO(getContext());
        listKH = daoKH.getDKve(String.valueOf(1));
        listKH2 = daoKH.getDKve(String.valueOf(2));
        listKH0 = daoKH.getDKve(String.valueOf(0));
        int Trong1 = 0;
        int Trong2 = 0;
        int Trong0 = 0;
        for(int i=0; i<listKH.size(); i++){
            if(String.valueOf(listKH.get(i).getIdKH())!=null) {
                listXe = daoXe.getDangSd1(String.valueOf(listKH.get(i).getIdKH()), String.valueOf(0));
                for (int j = 0; j < list.size(); j++) {
                    Trong1 += 1;
                }
            }
        }
        for(int i=0; i<listKH2.size(); i++){
            if(String.valueOf(listKH2.get(i).getIdKH())!=null){
                listXe = daoXe.getDangSd1(String.valueOf(listKH2.get(i).getIdKH()), String.valueOf(0));
                for(int j=0; j<list.size(); j++){
                    Trong1 += 1;
                }
            }

        }
        for(int i=0; i<listKH0.size(); i++){
            if(String.valueOf(listKH0.get(i).getIdKH())!=null){
                listXe = daoXe.getDangGui(String.valueOf(listKH0.get(i).getIdKH()), String.valueOf(0));
                for(int j=0; j<list.size(); j++){
                    Trong0 += 1;
                }
            }
        }
        int Trong = 100 - (Trong1 + Trong2 + Trong0);
        btnQuet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Trong != 0){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE);
                    ScanOptions intentIntegrator = new ScanOptions();
                    intentIntegrator.setPrompt("QRCode");
                    intentIntegrator.setBeepEnabled(true);
                    intentIntegrator.setOrientationLocked(true);
                    intentIntegrator.setCaptureActivity(CaptureAct.class);
                    barLauncher.launch(intentIntegrator);
                }else {
                    Toast.makeText(getContext(), "Bãi đỗ xe hết chỗ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null){
            daoNV = new NhanVienDAO(getContext());
            daoXe = new XeDAO(getContext());
            daoHD = new HoaDonDAO(getContext());
            daoKH = new KhachHangDAO(getContext());
            listXe = daoXe.getAll();
            Xe xe = daoXe.getBienSo(result.getContents());
            int statusGuiXe = xe.getStatusGuiXe();
            if(statusGuiXe == 1){
                xe.setStatusGuiXe(0);
                if (daoXe.UpdateSttGX(xe)) {
                    Toast.makeText(getContext(), "Xe đang gửi", Toast.LENGTH_SHORT).show();
                    listXe.clear();
                    listXe.addAll(daoXe.getAll());
                }
                int idXe = xe.getIdXe();
                int idKh = xe.getIdKH();
                int htTT = 0;
                int tienTT = 0;
                Date objDate = new Date(System.currentTimeMillis());
                DateFormat dateFormat = new DateFormat();
                String chuoi_ngay_thang_nam = (String) dateFormat.format("yyyy/MM/dd", objDate);
                Date tgVao = null;
                try {
                    tgVao = simpleDateFormat.parse(chuoi_ngay_thang_nam);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date tgRa = null;
                try {
                    tgRa = simpleDateFormat.parse(chuoi_ngay_thang_nam);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                byte[] anhVao = ConverttoArrayByte(imgQR);
                byte[] anhRa = ConverttoArrayByte(imgQR);
                int statusHD = 0;
                int sttGuiXe = 0;

                HoaDon hd = new HoaDon(idXe, htTT, tienTT, idKh, tgVao, tgRa, anhVao, anhRa, statusHD, sttGuiXe);
                if(daoHD.Insert(hd)){
                    Toast.makeText(getContext(), "Quét thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(daoHD.getAll());
                } else {
                    Toast.makeText(getContext(), "Quét thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_qr, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
                list = daoHD.getAll();
                HoaDon hd = list.get(list.size() - 1);
                hd.setHaRa(ConverttoArrayByte(imgQR));
                daoHD.Update(hd);
                ImageView img = view.findViewById(R.id.img_genQR);
                list = daoHD.getAll();
                String bienSoXe = xe.getBienSoXe();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(bienSoXe), BarcodeFormat.QR_CODE, 300, 300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    img.setImageBitmap(bitmap);
                }catch (WriterException e){
                    throw new RuntimeException(e);
                }
            }
        }
    });

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgQR.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] ConverttoArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}

