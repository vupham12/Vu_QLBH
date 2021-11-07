package com.example.vu_qlbh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtHoTen;
    EditText edtmatkhau;
    Button btndangnhap;
    Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtHoTen = (EditText) findViewById(R.id.editTextHoTen);
        edtmatkhau = (EditText) findViewById(R.id.editTextMatKhau);
        btndangnhap = (Button) findViewById(R.id.dangnhap);
        btnCancel = (Button) findViewById(R.id.buttonCancel);

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case (R.id.dangnhap):
                        if (edtHoTen.getText().toString().equals("admin") && edtmatkhau.getText().toString().equals("12345"))
                        {Toast.makeText(getApplicationContext(), "Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        Intent ds  = new Intent(MainActivity.this, Vu_list.class);
                        startActivity(ds);}
                        else
                            Toast.makeText(getApplicationContext(), "Đăng nhập không thành công!",Toast.LENGTH_SHORT).show();
                        break;
                    case (R.id.buttonCancel):
                        break;
            }
            }
        });
    }
}
