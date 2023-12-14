package com.example.demo_quanlyhappy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_quanlyhappy.DAO.KhachHangDAO;
import com.example.demo_quanlyhappy.DAO.NhanVienDAO;
import com.example.demo_quanlyhappy.DTO.KhachHang;
import com.example.demo_quanlyhappy.DTO.NapTien;
import com.example.demo_quanlyhappy.DTO.NhanVien;
import com.example.demo_quanlyhappy.Fragment.FragQuetNV;
import com.example.demo_quanlyhappy.Fragment.FragQuetQR;
import com.example.demo_quanlyhappy.Fragment.FragThongKeKH;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.util.Date;

public class MainLogin extends AppCompatActivity {
    TextInputLayout edUsename, edPassword;
    Button btnLogin;
    CheckBox luuMK;
    TextView tvQuenMk, tvDangKy;
    KhachHangDAO KHdao;
    NhanVienDAO NVdao;
    String strUser, strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        edUsename = findViewById(R.id.input_edt_username);
        edPassword = findViewById(R.id.input_edit_password);
        btnLogin = findViewById(R.id.btn_Login_main);
        tvQuenMk = findViewById(R.id.tv_quenMK);
        tvDangKy = findViewById(R.id.tv_DangKy);
        luuMK = findViewById(R.id.luu_mk);
        KHdao = new KhachHangDAO(this);
        NVdao = new NhanVienDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean luuMk = pref.getBoolean("REMEMBER", false);

        edUsename.getEditText().setText(user);
        edPassword.getEditText().setText(pass);
        luuMK.setChecked(luuMk);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                checkLogin();
            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainLogin.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_dk_kh, null);
                builder.setView(view);
                builder.create().getWindow().clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                builder.create().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                Dialog dialog = builder.create();
                dialog.show();

                TextInputLayout inputTenKH = view.findViewById(R.id.intput_dk_tenKH);
                TextInputLayout inputCccdKH = view.findViewById(R.id.intput_dk_cccdKH);
                TextInputLayout inputSdtKH = view.findViewById(R.id.intput_dk_sdtKH);
                TextInputLayout inputPassKH = view.findViewById(R.id.intput_dk_passKH);
                TextInputLayout inputRePassKH = view.findViewById(R.id.intput_dk_repassKH);
                Button btnSave = view.findViewById(R.id.btn_save_dk_KH);
                Button btnHuy = view.findViewById(R.id.btn_exit_dk_KH);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String hoTen = inputTenKH.getEditText().getText().toString();
                        String Cccd = inputCccdKH.getEditText().getText().toString();
                        String Sdt = inputSdtKH.getEditText().getText().toString();
                        String Pass = inputPassKH.getEditText().getText().toString();
                        int soDu = 0;
                        int goiDK = 0;
                        KhachHang kh = new KhachHang(Cccd,Sdt,Pass, hoTen, soDu, goiDK);
                        if (KHdao.Insert(kh)) {
                            Toast.makeText(MainLogin.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(MainLogin.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            edit.clear();
        }else{
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }
    public void checkLogin(){
        strUser = edUsename.getEditText().getText().toString();
        strPass = edPassword.getEditText().getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else {
            if(KHdao.checkLogin(strUser,strPass)>0){
                KhachHang kh = KHdao.getID(strUser);
                FragThongKeKH frag_thongKeKH = new FragThongKeKH();
                Bundle bundle = new Bundle();
                bundle.putSerializable("quyen1", kh);
                frag_thongKeKH.setArguments(bundle);
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, luuMK.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainChinh.class);
                intent.putExtra("user", strUser);
                intent.putExtras(bundle);
                startActivity(intent);
            }else if(NVdao.checkLogin(strUser,strPass)>0){
                NhanVien nv = NVdao.getID(strUser);
                if(nv.getStatusNV()==1){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("quyen", nv);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(strUser, strPass, luuMK.isChecked());
                    Intent intent = new Intent(getApplicationContext(), MainChinh.class);
                    intent.putExtra("user", strUser);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Tài khoản ngưng hoạt động", Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}