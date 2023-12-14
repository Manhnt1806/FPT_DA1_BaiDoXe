package com.example.demoqrcode;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    Button btnQuet;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnQuet = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        btnQuet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanOptions intentIntegrator = new ScanOptions();
                intentIntegrator.setPrompt("QRCode");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(CaptureAct.class);
                barLauncher.launch(intentIntegrator);
            }
        });
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents()!=null){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("QR");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (intentResult != null) {
//            String contents = intentResult.getContents();
//            if (contents != null) {
//                tv.setText(intentResult.getContents());
//            }
//
//
//        }
//
//    }
}