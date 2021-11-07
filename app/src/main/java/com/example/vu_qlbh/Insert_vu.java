package com.example.vu_qlbh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Insert_vu extends AppCompatActivity {
    EditText txtTenCs, txtTenBH;
    Button btnChonHinh, btnChupHinh, btnLuu, btnHuy;
    ImageView imgAnh;
    SQLiteDatabase database;
    String DATABASE_NAME ="Minhvu_QLBaiHat.db";
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vu);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Insert_vu.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
    }

    private void insert() {
        String tenBH = txtTenBH.getText().toString();
        String tenCS = txtTenCs.getText().toString();
        byte[] anh = getByteArrayFromImageView(imgAnh);

        ContentValues contentValues = new ContentValues();
        contentValues.put("TenBH",tenBH);
        contentValues.put("tencs",tenCS);
        contentValues.put("Anh",anh);

        database = Database.initDatabase(Insert_vu.this, DATABASE_NAME);
        database.insert("NhanVien",null,contentValues);

        Intent intent = new Intent(Insert_vu.this,MainActivity.class);
        startActivity(intent);



    }
    public byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }



    private void addControl() {
        txtTenBH = (EditText) findViewById(R.id.editTextTenBH);
        txtTenCs = (EditText) findViewById(R.id.editTextTenCS);
        btnChonHinh = (Button) findViewById(R.id.buttonChonHinhThem);
        btnChupHinh = (Button) findViewById(R.id.buttonChupHinhThem);
        btnLuu = (Button) findViewById(R.id.buttonLuuThem);
        btnHuy = (Button) findViewById(R.id.buttonHuyThem);
        imgAnh = (ImageView)findViewById(R.id.imageViewAnh);

    }

    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }
    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgAnh.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgAnh.setImageBitmap(bitmap);
            }
        }
    }
}


