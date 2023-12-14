package com.example.demosqlwithimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity2 extends AppCompatActivity {
    EditText edtten, edtgia;
    Button btnthem,btndanhsach;
    ImageView imganh;
    private int REQUEST_CODE;
    public static DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtten= (EditText) findViewById(R.id.edtTen);
        edtgia= (EditText) findViewById(R.id.edtGiatien);
        btnthem= (Button) findViewById(R.id.btnThemSP);
        btndanhsach= (Button) findViewById(R.id.btnXemDS);
        imganh= (ImageView) findViewById(R.id.imageView);
        imganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.Insertsanpham(edtten.getText().toString(), Integer.parseInt(edtgia.getText().toString()),ConverttoArrayByte(imganh));
                Toast.makeText(MainActivity2.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                edtgia.setText("");
                edtten.setText("");
                edtten.requestFocus();
            }
        });
        btndanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this,DanhSachSP.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imganh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}